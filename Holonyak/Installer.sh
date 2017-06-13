#Holonyak Installer shell
#Made by Kell Larson 2017

# Current software version
VERSION=1.0
# Folder the program will be stored in
FOLDER=/usr/local/Holonyak-V$VERSION
# Permissions for the Capstone.jar
PERM=777
# The author
AUTHOR=Kell_Larson

# echo relevant info
echo Holonyak Version $VERSION Install script started
echo @author $AUTHOR 2017
echo Root priveledges required in order to install to /usr/local
# Create the program folder
sudo mkdir $FOLDER
echo Directory created at $FOLDER
# Copy the files
sudo cp -r lib $FOLDER
sudo cp Capstone.jar $FOLDER/Capstone.jar
echo Libraries copied to $FOLDER
# Give Capstone.jar executable permissions (at least)
chmod $PERM $FOLDER/Capstone.jar
echo Giving the main jarfile $PERM permissions
# Copy the .desktop file to the desktop
sudo cp Holonyak.desktop $HOME/Desktop/Holonyak.desktop
echo .desktop file copied to the Desktop
echo Done.

# This program is named Holonyak, after the engineer Nick Holonyak,
# who created the first LED to emit visible light. He also invented
# laser diodes (which drive all CD and DVD drives)
# He is currently researching quantum dot lasers.
