# Pretend-Boot

Typically I write tests to make sure my API is sane, and I write tests
to make sure my JS behaves appropriately given the API is sane. But I
end up writing feature tests that seem redundant, based on fear that
I'm going to break the contract on either the server side or the
client side.

My intention with this library is to be able to easily capture _actual_
behavior of my API for me to run my js tests against, without having
to spin up an entire server. Every time I change my java code I can
know whether or not I've broken an assumption my JS code makes, and
vise versa.

## Usage

1. Capture some requests

Add a BehaviorRecordingResultHandler to an existing API test that
you'd like to treat as a fixture on the javascript side.

```
@Test
public void someExistingApiTestIWantToExposeToJavascript() {
    this.mockMvc.perform(
                MockMvcRequestBuilders.get("/something"))
                    .andDo(saveFixture("js/spec/behavior"))
                .andExpect(...);
}
```
(see src/test/java/RecordingTest.java)

You can do this to as many tests as you want.


2. Make those request fixtures available to tests

```
const loadApiBehavior = require('./loadApiBehavior');

module.exports = {
    ...,
    plugins: [new webpack.DefinePlugin({
        API_BEHAVIOR: JSON.stringify(loadApiBehavior("./spec/behavior"))
    })]
};
```
(see js/webpack.config.js)


3. Activate your fixtures in your integration tests

```
    let pretendBoot = new PretendBoot();

    beforeEach(() => {
        pretendBoot.start(API_BEHAVIOR);
    });

    afterEach(() => {
        pretendBoot.stop();
    });
    
    it("can now act as though our API is running!", () => {
        /* if I make a request to one of the endpoints specified in spec/behavior,
           I should get the response I captured earlier */
    });    
```
(see js/spec/integration_spec.js)


4. Write fewer feature tests!

While pretend-boot is activated your JS tests can pretend your
real server is running, without many of the costs associated with
feature tests!


## TODO

- try it from an application
- Publish to npm
- Publish to maven
- Probably other stuff too