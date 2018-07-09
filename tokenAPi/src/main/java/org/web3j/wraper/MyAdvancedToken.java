package org.web3j.wraper;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

import java.math.BigInteger;
import java.util.*;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.3.0.
 */
public class MyAdvancedToken extends Contract {
    private static final String BINARY = "0x60606040526003805460ff19166006179055341561001c57600080fd5b604051610e34380380610e34833981016040528080519190602001805182019190602001805160008054600160a060020a033316600160a060020a03199091168117825560035460ff16600a0a870260048190559082526005602052604090912055909101905082828260018280516100999291602001906100b9565b5060028180516100ad9291602001906100b9565b50505050505050610154565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100fa57805160ff1916838001178555610127565b82800160010185558215610127579182015b8281111561012757825182559160200191906001019061010c565b50610133929150610137565b5090565b61015191905b80821115610133576000815560010161013d565b90565b610cd1806101636000396000f3006060604052600436106101275763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166305fefda7811461012c57806306fdde0314610147578063095ea7b3146101d157806318160ddd1461020757806323b872dd1461022c578063313ce5671461025457806342966c681461027d5780634b750334146102935780635d36d182146102a657806370a08231146102c857806379cc6790146102e75780638620410b146103095780638da5cb5b1461031c57806395d89b411461034b578063a6f2ae3a1461035e578063a9059cbb14610366578063b414d4b614610388578063cae9ca51146103a7578063dd62ed3e1461040c578063e4849b3214610431578063e724529c14610447578063f2fde38b1461046b575b600080fd5b341561013757600080fd5b61014560043560243561048a565b005b341561015257600080fd5b61015a6104b0565b60405160208082528190810183818151815260200191508051906020019080838360005b8381101561019657808201518382015260200161017e565b50505050905090810190601f1680156101c35780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156101dc57600080fd5b6101f3600160a060020a036004351660243561054e565b604051901515815260200160405180910390f35b341561021257600080fd5b61021a61057e565b60405190815260200160405180910390f35b341561023757600080fd5b6101f3600160a060020a0360043581169060243516604435610584565b341561025f57600080fd5b6102676105fb565b60405160ff909116815260200160405180910390f35b341561028857600080fd5b6101f3600435610604565b341561029e57600080fd5b61021a61068f565b34156102b157600080fd5b610145600160a060020a0360043516602435610695565b34156102d357600080fd5b61021a600160a060020a0360043516610750565b34156102f257600080fd5b6101f3600160a060020a0360043516602435610762565b341561031457600080fd5b61021a61083e565b341561032757600080fd5b61032f610844565b604051600160a060020a03909116815260200160405180910390f35b341561035657600080fd5b61015a610853565b6101456108be565b341561037157600080fd5b6101f3600160a060020a03600435166024356108de565b341561039357600080fd5b6101f3600160a060020a03600435166108f4565b34156103b257600080fd5b6101f360048035600160a060020a03169060248035919060649060443590810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061090995505050505050565b341561041757600080fd5b61021a600160a060020a0360043581169060243516610a37565b341561043c57600080fd5b610145600435610a54565b341561045257600080fd5b610145600160a060020a03600435166024351515610ab7565b341561047657600080fd5b610145600160a060020a0360043516610b43565b60005433600160a060020a039081169116146104a557600080fd5b600791909155600855565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105465780601f1061051b57610100808354040283529160200191610546565b820191906000526020600020905b81548152906001019060200180831161052957829003601f168201915b505050505081565b600160a060020a033381166000908152600660209081526040808320938616835292905220819055600192915050565b60045481565b600160a060020a038084166000908152600660209081526040808320339094168352929052908120548211156105b957600080fd5b600160a060020a03808516600090815260066020908152604080832033909416835292905220805483900390556105f1848484610b8d565b5060019392505050565b60035460ff1681565b600160a060020a0333166000908152600560205260408120548290101561062a57600080fd5b600160a060020a03331660008181526005602052604090819020805485900390556004805485900390557fcc16f5dbb4873280815c1ee09dbd06736cffcc184412cf7a71a0fdb75d397ca59084905190815260200160405180910390a2506001919050565b60075481565b60005433600160a060020a039081169116146106b057600080fd5b600160a060020a038216600090815260056020526040902054819010156106d657600080fd5b600160a060020a0380831660009081526005602052604080822080548590039055815490921681528190208054830190557f8ace08257bdebb8add7a028821351d33330dd948895f986b2636c7a439bcca18908390839051600160a060020a03909216825260208201526040908101905180910390a15050565b60056020526000908152604090205481565b600160a060020a0382166000908152600560205260408120548290101561078857600080fd5b600160a060020a03808416600090815260066020908152604080832033909416835292905220548211156107bb57600080fd5b600160a060020a038084166000818152600560209081526040808320805488900390556006825280832033909516835293905282902080548590039055600480548590039055907fcc16f5dbb4873280815c1ee09dbd06736cffcc184412cf7a71a0fdb75d397ca59084905190815260200160405180910390a250600192915050565b60085481565b600054600160a060020a031681565b60028054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105465780601f1061051b57610100808354040283529160200191610546565b6000600854348115156108cd57fe5b0490506108db303383610b8d565b50565b60006108eb338484610b8d565b50600192915050565b60096020526000908152604090205460ff1681565b600083610916818561054e565b15610a2f5780600160a060020a0316638f4ffcb1338630876040518563ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018085600160a060020a0316600160a060020a0316815260200184815260200183600160a060020a0316600160a060020a0316815260200180602001828103825283818151815260200191508051906020019080838360005b838110156109cc5780820151838201526020016109b4565b50505050905090810190601f1680156109f95780820380516001836020036101000a031916815260200191505b5095505050505050600060405180830381600087803b1515610a1a57600080fd5b5af11515610a2757600080fd5b505050600191505b509392505050565b600660209081526000928352604080842090915290825290205481565b60075430908202600160a060020a038216311015610a7157600080fd5b610a7c333084610b8d565b33600160a060020a03166108fc60075484029081150290604051600060405180830381858888f193505050501515610ab357600080fd5b5050565b60005433600160a060020a03908116911614610ad257600080fd5b600160a060020a03821660009081526009602052604090819020805460ff19168315151790557f48335238b4855f35377ed80f164e8c6f3c366e54ac00b96a6402d4a9814a03a5908390839051600160a060020a039092168252151560208201526040908101905180910390a15050565b60005433600160a060020a03908116911614610b5e57600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b600160a060020a0382161515610ba257600080fd5b600160a060020a03831660009081526005602052604090205481901015610bc857600080fd5b600160a060020a0382166000908152600560205260409020548181011015610bef57600080fd5b600160a060020a03831660009081526009602052604090205460ff1615610c1557600080fd5b600160a060020a03821660009081526009602052604090205460ff1615610c3b57600080fd5b600160a060020a038084166000818152600560205260408082208054869003905592851680825290839020805485019055917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9084905190815260200160405180910390a35050505600a165627a7a7230582018cbadb99ae4b22961f44140b222ed8623a3bf9bc81bf3874af773d229cf7b7d0029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
        _addresses.put("257291", "0x23102b0b29106d132a88fd79c2e9c712617291b4");
        _addresses.put("1530945357371", "0x492126a61281cd699e168914e8cccd7865ccb88e");
        _addresses.put("1530863571295", "0x372b97c17c176fed2a1f295372d9253b755885a7");
        _addresses.put("1531100796513", "0x79f6f05ae8edf9bd98fff45fb16b6dbff7db6065");
    }

    protected MyAdvancedToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MyAdvancedToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<FrozenFundsEventResponse> getFrozenFundsEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("FrozenFunds", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<FrozenFundsEventResponse> responses = new ArrayList<FrozenFundsEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FrozenFundsEventResponse typedResponse = new FrozenFundsEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.target = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.frozen = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<FrozenFundsEventResponse> frozenFundsEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("FrozenFunds", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, FrozenFundsEventResponse>() {
            @Override
            public FrozenFundsEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                FrozenFundsEventResponse typedResponse = new FrozenFundsEventResponse();
                typedResponse.log = log;
                typedResponse.target = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.frozen = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<RecycleFoudsEventResponse> getRecycleFoudsEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("recycleFouds", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<RecycleFoudsEventResponse> responses = new ArrayList<RecycleFoudsEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RecycleFoudsEventResponse typedResponse = new RecycleFoudsEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.target = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RecycleFoudsEventResponse> recycleFoudsEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("recycleFouds", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, RecycleFoudsEventResponse>() {
            @Override
            public RecycleFoudsEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                RecycleFoudsEventResponse typedResponse = new RecycleFoudsEventResponse();
                typedResponse.log = log;
                typedResponse.target = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Transfer", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Transfer", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public List<BurnEventResponse> getBurnEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Burn", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<BurnEventResponse> responses = new ArrayList<BurnEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BurnEventResponse typedResponse = new BurnEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<BurnEventResponse> burnEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Burn", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, BurnEventResponse>() {
            @Override
            public BurnEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                BurnEventResponse typedResponse = new BurnEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<String> name() {
        Function function = new Function("name", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        Function function = new Function(
                "approve", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> totalSupply() {
        Function function = new Function("totalSupply", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transferFrom(String _from, String _to, BigInteger _value) {
        Function function = new Function(
                "transferFrom", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_from), 
                new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> decimals() {
        Function function = new Function("decimals", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> burn(BigInteger _value) {
        Function function = new Function(
                "burn", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> sellPrice() {
        Function function = new Function("sellPrice", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> balanceOf(String param0) {
        Function function = new Function("balanceOf", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> burnFrom(String _from, BigInteger _value) {
        Function function = new Function(
                "burnFrom", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_from), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> buyPrice() {
        Function function = new Function("buyPrice", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> symbol() {
        Function function = new Function("symbol", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        Function function = new Function(
                "transfer", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> frozenAccount(String param0) {
        Function function = new Function("frozenAccount", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> approveAndCall(String _spender, BigInteger _value, byte[] _extraData) {
        Function function = new Function(
                "approveAndCall", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.web3j.abi.datatypes.DynamicBytes(_extraData)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> allowance(String param0, String param1) {
        Function function = new Function("allowance", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0), 
                new org.web3j.abi.datatypes.Address(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String newOwner) {
        Function function = new Function(
                "transferOwnership", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<MyAdvancedToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialSupply, String tokenName, String tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(initialSupply), 
                new org.web3j.abi.datatypes.Utf8String(tokenName), 
                new org.web3j.abi.datatypes.Utf8String(tokenSymbol)));
        return deployRemoteCall(MyAdvancedToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<MyAdvancedToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialSupply, String tokenName, String tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(initialSupply), 
                new org.web3j.abi.datatypes.Utf8String(tokenName), 
                new org.web3j.abi.datatypes.Utf8String(tokenSymbol)));
        return deployRemoteCall(MyAdvancedToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public RemoteCall<TransactionReceipt> freezeAccount(String target, Boolean freeze) {
        Function function = new Function(
                "freezeAccount", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(target), 
                new org.web3j.abi.datatypes.Bool(freeze)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setPrices(BigInteger newSellPrice, BigInteger newBuyPrice) {
        Function function = new Function(
                "setPrices", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(newSellPrice), 
                new org.web3j.abi.datatypes.generated.Uint256(newBuyPrice)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> buy(BigInteger weiValue) {
        Function function = new Function(
                "buy", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> sell(BigInteger amount) {
        Function function = new Function(
                "sell", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> recycle(String target, BigInteger amount) {
        Function function = new Function(
                "recycle", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(target), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static MyAdvancedToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MyAdvancedToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static MyAdvancedToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MyAdvancedToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class FrozenFundsEventResponse {
        public Log log;

        public String target;

        public Boolean frozen;
    }

    public static class RecycleFoudsEventResponse {
        public Log log;

        public String target;

        public BigInteger amount;
    }

    public static class TransferEventResponse {
        public Log log;

        public String from;

        public String to;

        public BigInteger value;
    }

    public static class BurnEventResponse {
        public Log log;

        public String from;

        public BigInteger value;
    }
}
