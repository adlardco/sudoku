export default class Timer {

    private readonly intervalMilliseconds: number;
    private readonly callback: () => void;
    private intervalId: NodeJS.Timeout | null;

    constructor(intervalMilliseconds: number, callback: () => void) {
        this.intervalMilliseconds = intervalMilliseconds;
        this.callback = callback;
        this.intervalId = null;
    }

    start() {
        if(this.intervalId === null) {
            this.callback();
            this.intervalId = setInterval(this.callback, this.intervalMilliseconds);
        }
    }

    stop() {
        if(this.intervalId !== null) {
            clearInterval(this.intervalId);
            this.intervalId = null;
        }
    }
}