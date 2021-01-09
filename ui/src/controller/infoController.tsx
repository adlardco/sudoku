import RestController from './restController';
import InfoModel from '../model/infoModel';

export default class InfoController {

  private static readonly PathPrefix = 'api';

  private readonly controller: RestController;

  constructor(controller: RestController) {
    this.controller = controller;
  }

  getInfo(callback: (info: InfoModel) => void, errorCallback: (e: Error) => void) {
    this.controller.get(this.endpoint('info'), callback, errorCallback);
  }

  private endpoint(methodName: string): string {
    return `${InfoController.PathPrefix}/${methodName}`;
  }
}