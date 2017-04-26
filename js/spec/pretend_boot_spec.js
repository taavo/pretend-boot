import { PretendBoot } from '../index'

describe("PretendBoot", () => {
    let pretendBoot = new PretendBoot();

    beforeEach(() => {
        pretendBoot.start([{"request":{"method":"GET","url":"/aSpecifiedPath"},"response":{"status":200,"responseText":"The Response You Were Expecting"}}]);
    });

    afterEach(() => {
        pretendBoot.stop();
    });

    it("responds according to specified requests", (done) => {
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