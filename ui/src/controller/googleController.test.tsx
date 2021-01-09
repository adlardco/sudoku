import RestController from './restController';
import GoogleController from './googleController';
import { mock } from 'jest-mock-extended';

describe('GoogleController', () => {

  let restController: RestController;
  let controller: GoogleController;

  beforeEach(() => {
    restController = mock<RestController>();
    controller = new GoogleController(restController);
  });

  it('calls rest controller on ping', () => {
      const callback = () => {};
      controller.ping(callback);
      expect(restController.head).toHaveBeenCalledWith(callback);
  });
});