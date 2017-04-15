import 'jasmine-ajax';

export class FakeServer {
    start(apiBehavior) {
        jasmine.Ajax.install();

        apiBehavior.forEach((apiBehavior) => {
            const request = apiBehavior.request;
            const response = apiBehavior.response;

            jasmine.Ajax.stubRequest(request.url, null, request.method).andReturn(response);
        });
    }

    stop() {
        jasmine.Ajax.uninstall();
    }
}