// open mongo shell with database owner/admin 
	// if you have already set auth = true (conf file) then you need to be dbOwner or userAdmin
	// if you want to specify onw manually:
	// db.createUser({ user:"<owner or admin name>", pwd:"<password>", roles:["dbOwner"] })
	// mongo eebf -u <username> -p
// load the script with
// load("<file location>")

db.createUser({ user:"eeBF_Admin", pwd:"Tombstone", roles:["dbAdmin", "readWrite"] });
db.createUser({ user:"eeBF_Kunde", pwd:"Silverado", roles:["readWrite"] });
db.createUser({ user:"eeBF_Benutzer", pwd:"Unforgiven", roles:["read"] });
