PROPS_FILE="../user.properties"

touch "$PROPS_FILE"
echo "oauthToken = $OAUTH_TOKEN" >> "$PROPS_FILE"
