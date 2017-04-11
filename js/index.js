import 'jasmine-ajax';

export class FakeServer {
    start() {
        jasmine.Ajax.install();

        API_BEHAVIOR.forEach((apiBehavior) => {
            const request = apiBehavior.request;
            const response = apiBehavior.response;

            jasmine.Ajax.stubRequest(request.url, null, request.method).andReturn(response);
        });
    }

    stop() {
        jasmine.Ajax.uninstall();
    }
}