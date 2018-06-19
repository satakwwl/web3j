var Migrations = artifacts.require("./Migrations.sol");
var Token= artifacts.require("./TokenERC20.sol");
//var User= artifacts.require("./User.sol");

module.exports = function(deployer) {
  deployer.deploy(Migrations);
  //eployer.deploy(User);
  deployer.deploy(Token,10000000,'johncoin','johncoin');
};
