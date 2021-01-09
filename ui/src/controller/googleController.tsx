import RestController from './restController';

export default class GoogleController {

  private readonly controller: RestController;

  constructor(controller: RestController) {
    this.controller = controller;
  }

  ping(callback: (ok: boolean) => void) {
    this.controller.head(callback);
  }
}