var Migrations = artifacts.require("./Migrations.sol");
//var Token= artifacts.require("./TokenERC20.sol");
var User= artifacts.require("./User.sol");

module.exports = function(deployer) {
  deployer.deploy(Migrations);
  deployer.deploy(User);
 // deployer.deploy(Token,10000000,'integral','integral');
};
