module.exports = {
  networks: {
      development: {
            host: "192.168.1.189",
            port: 8545,
            network_id: "*", // Match any network id,
            gas:3000000
          }
    },
  solc: {
    optimizer: {
        enabled: true,
        runs: 200
      }
  }
};
