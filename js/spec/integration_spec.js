import { PretendBoot } from '../index'

describe("PretendBoot", () => {
    let pretendBoot = new PretendBoot();

    beforeEach(() => {
        pretendBoot.start(API_BEHAVIOR);
    });

    afterEach(() => {
        pretendBoot.stop();
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