var Migrations = artifacts.require("./Migrations.sol");
var Token= artifacts.require("./MyAdvancedToken.sol");
//var User= artifacts.require("./User.sol");

module.exports = function(deployer) {
  deployer.deploy(Migrations);
//  deployer.deploy(User);
  deployer.deploy(Token,10000000000,'RPJ','RPJ');
};
