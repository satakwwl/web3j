package org.web3j.wraper;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
public class User extends Contract {
    private static final String BINARY = "0x6060604052341561000f57600080fd5b6104a88061001e6000396000f30060606040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680633e10510b14610051578063da465d741461010c575b600080fd5b341561005c57600080fd5b610091600480803590602001908201803590602001919091929080359060200190820180359060200191909192905050610162565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100d15780820151818401526020810190506100b6565b50505050905090810190601f1680156100fe5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561011757600080fd5b610160600480803590602001908201803590602001919091929080359060200190820180359060200191909192908035906020019082018035906020019190919290505061025d565b005b61016a6103c3565b600080868660405180838380828437820191505092505050908152602001604051809103902060010184846040518083838082843782019150509250505090815260200160405180910390209050808054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561024d5780601f106102225761010080835404028352916020019161024d565b820191906000526020600020905b81548152906001019060200180831161023057829003601f168201915b5050505050915050949350505050565b7fdabb8ae608e4e175e2e2619d0daeb5b4a9c62fa3e08058737d862eba72efa67f60008787604051808383808284378201915050925050509081526020016040518091039020600101858560405180838380828437820191505092505050908152602001604051809103902060405180806020018281038252838181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156103525780601f1061032757610100808354040283529160200191610352565b820191906000526020600020905b81548152906001019060200180831161033557829003601f168201915b50509250505060405180910390a1818160008888604051808383808284378201915050925050509081526020016040518091039020600101868660405180838380828437820191505092505050908152602001604051809103902091906103ba9291906103d7565b50505050505050565b602060405190810160405280600081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061041857803560ff1916838001178555610446565b82800160010185558215610446579182015b8281111561044557823582559160200191906001019061042a565b5b5090506104539190610457565b5090565b61047991905b8082111561047557600081600090555060010161045d565b5090565b905600a165627a7a723058202beb5492c12e025447f4ff65fa3f2f102b75f64b92d222ce00e259181bc426e40029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
        _addresses.put("5678", "0x66a7edd10fb8a17056d2828191e8ae93a1e34d70");
    }

    protected User(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected User(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<SetEventEventResponse> getSetEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("setEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<SetEventEventResponse> responses = new ArrayList<SetEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SetEventEventResponse typedResponse = new SetEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.value = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SetEventEventResponse> setEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("setEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, SetEventEventResponse>() {
            @Override
            public SetEventEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                SetEventEventResponse typedResponse = new SetEventEventResponse();
                typedResponse.log = log;
                typedResponse.value = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<String> get(String _user, String _pro) {
        Function function = new Function("get", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_user), 
                new org.web3j.abi.datatypes.Utf8String(_pro)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> set(String _user, String _pro, String _value) {
        Function function = new Function(
                "set", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_user), 
                new org.web3j.abi.datatypes.Utf8String(_pro), 
                new org.web3j.abi.datatypes.Utf8String(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<User> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(User.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<User> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(User.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static User load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new User(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static User load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new User(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class SetEventEventResponse {
        public Log log;

        public String value;
    }
}
