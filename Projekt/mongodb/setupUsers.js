// open mongo shell with database owner/admin 
	// (you need to have one specified by :
	// db.createUser({ user:"<owner or admin name>", pwd:"<password>", roles:["dbOwner"] })
// mongo -u <owner or admin> -p
// then load the script with
// load("<file location>")

use eebf;
db.createUser({ user:"eeBF_Admin", pwd:"Tombstone", roles:["dbAdmin"] });
db.createUser({ user:"eeBF_Kunde", pwd:"Silverado", roles:["readWrite"] });
db.createUser({ user:"eeBF_Benutzer", pwd:"Unforgiven", roles:["read"] });
