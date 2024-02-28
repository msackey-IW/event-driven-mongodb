#!/bin/bash

echo "Creating MongoDB database and collection..."

mongo <<EOF
use users
db.mycollection.insertOne({ "name": "example" })
EOF

echo "MongoDB database and collection creation completed."
