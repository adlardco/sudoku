import RestController from './restController';
import InfoModel from '../model/infoModel';

export default class InfoController {

  private static readonly Path = 'info';

  private readonly controller: RestController;

  constructor(controller: RestController) {
    this.controller = controller;
  }

  getInfo(callback: (info: InfoModel) => void, errorCallback: (e: Error) => void) {
    this.controller.get(InfoController.Path, callback, errorCallback);
  }
}