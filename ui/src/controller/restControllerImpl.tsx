import RestController from "./restController";

enum HttpMethod {
    HEAD = 'HEAD',
    GET = 'GET', 
    POST = 'POST'
}

export default class RestControllerImpl implements RestController {

    private readonly baseUrl: URL;
    private readonly timeout: number;

    constructor(baseUrl: URL, timeout: number) {
        this.baseUrl = baseUrl;
        this.timeout = timeout;
    }

    public async head(
        callback: (ok: boolean) => void) {
        try {
            const response = await this.fetch(this.url('').toString(), {
                method: HttpMethod.HEAD.toString(),
                mode: 'no-cors'
            });
            const status = response.status;
            return callback(status !== 200);
        } catch(e) {
            console.log(e.message);
            callback(false);
        }
    }

    public async get(
        endpoint: string,
        callback: (data: any) => void,
        errorCallback: (e: Error) => void) {
        try {
            const response = await this.fetch(this.url(endpoint).toString(), {
                method: HttpMethod.GET.toString()
            });
            const data = await response.json();
            return callback(data);
        } catch(e) {
            errorCallback(e);
        }
    }

    public async post(
        endpoint: string, 
        body: string, 
        callback: (data: any) => void,
        errorCallback: (e: Error) => void
    ) {
        try {
            const response = await this.fetch(this.url(endpoint).toString(), {
                method: HttpMethod.POST.toString(),
                headers: { 'Content-Type': 'application/json' },
                body: body
            });
            const data = await response.json();
            return callback(data);
        } catch(e) {
            errorCallback(e);
        }
    }

    private fetch = async (requestInfo: RequestInfo, options: any) => {
        const { timeout = this.timeout } = options;
        const controller = new AbortController();
        const id = setTimeout(() => controller.abort(), timeout);
        const response = await fetch(requestInfo, {
          ...options,
          signal: controller.signal  
        });
        clearTimeout(id);
        return response;
    }

    private url(endpoint: string): URL {
        return new URL(`${this.baseUrl}${endpoint}`);
    }
}