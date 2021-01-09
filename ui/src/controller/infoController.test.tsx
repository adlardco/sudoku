import GridModel from '../model/gridModel';
import RestController from './restController';
import InfoController from './infoController';
import { mock } from 'jest-mock-extended';

describe('InfoController', () => {

  let restController: RestController;
  let controller: InfoController;

  beforeEach(() => {
    restController = mock<RestController>();
    controller = new InfoController(restController);
  });

  it('calls rest controller on getInfo', () => {
      const callback = () => {};
      const errorCallback = () => {};
      controller.getInfo(callback, errorCallback);
      expect(restController.get).toHaveBeenCalledWith('api/info', callback, errorCallback);
  });
});