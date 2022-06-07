# Bank account kata

Think of your personal bank account experience. When in doubt, go for the simplest solution


## Requirements

- Deposit and Withdrawal
- Account statement (date, amount, balance)
- Statement printing

The expected result is a *service API*, and its underlying implementation, that meets the expressed needs. \
**Nothing more, especially no UI, no persistence.**


## User Stories

### US 1:

**In order to** save money \
**As a** bank client \
**I want to** make a deposit in my account


### US 2:

**In order to** retrieve some or all of my savings \
**As a** bank client \
**I want to** make a withdrawal from my account


### US 3:

**In order to** check my operations \
**As a** bank client \
**I want to** see the history (operation, date, amount, balance) of my operations



## Project environment

### Tests
```
mvn test
```

### Run
```
mvn clean package
java -jar target/bank-sgcib-1.0-SNAPSHOT-jar-with-dependencies.jar 
```

The server will run on port 8080

The application is running with in-memory persistence. \
There is an existing client referenced with the UUID 994daf74-c079-3b4f-8fe5-43b4afe7be36 \
You can use it to test the API.
