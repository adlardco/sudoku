export default class FeatureFlag {

    private readonly enabled: boolean;

    constructor(paramName: string) {
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
        this.enabled = urlParams.has(paramName);
    }

    isEnabled(): boolean {
       return this.enabled;
    }
}