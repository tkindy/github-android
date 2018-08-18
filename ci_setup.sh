PROPS_FILE="$CIRCLE_WORKING_DIRECTORY/user.properties"

touch $PROPS_FILE
echo "oauthToken = $OAUTH_TOKEN" >> $PROPS_FILE
