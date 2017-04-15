import { FakeServer } from '../index'

describe("FakeServer", () => {
    let fakeServer = new FakeServer();

    beforeEach(() => {
        fakeServer.start(API_BEHAVIOR);
    });

    afterEach(() => {
        fakeServer.stop();
    });

    it("responds to requests recorded by our API", (done) => {
        const request = new XMLHttpRequest();
        request.addEventListener("load", function() {
            expect(this.status).toEqual(200);
            expect(this.responseText).toEqual("Hello World");
            done()
        });

        request.open('GET', '/');
        request.send();
    });
});