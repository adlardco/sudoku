import GridModel from '../model/gridModel';
import RestController from './restController';
import GridController from './gridController';
import { mock } from 'jest-mock-extended';

describe('GridController', () => {

  let restController: RestController;
  let controller: GridController;

  beforeEach(() => {
    restController = mock<RestController>();
    controller = new GridController(restController);
  });

  it('calls rest controller on solve', () => {
      const grid = new GridModel([0, 1]);
      const callback = () => {};
      const errorCallback = () => {};
      controller.solve(grid, callback, errorCallback);
      expect(restController.post).toHaveBeenCalledWith('api/grid', '{\"cellValues\":[0,1]}', callback, errorCallback);
  });
});