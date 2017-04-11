import { FakeServer } from '../index'

describe("FakeServer", () => {
    let fakeServer = new FakeServer();

    beforeEach(() => {
        fakeServer.start();
    });

    afterEach(() => {
        fakeServer.stop();
    });

    it("responds according to json in spec/behavior/simple.json", (done) => {
        const request = new XMLHttpRequest();
        request.addEventListener("load", function() {
            expect(this.status).toEqual(200);
            expect(this.responseText).toEqual("The Response You Were Expecting");
            done()
        });

        request.open('GET', '/aSpecifiedPath');
        request.send();
    });
});