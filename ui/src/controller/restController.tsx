enum HttpMethod {
    HEAD = 'HEAD',
    GET = 'GET', 
    POST = 'POST'
}

export default interface RestController {

    head: (callback: (ok: boolean) => void) => void,
    get: (endpoint: string, callback: (data: any) => void, errorCallback: (e: Error) => void) => void,
    post: (endpoint: string, body: string, callback: (data: any) => void, errorCallback: (e: Error) => void) => void
}