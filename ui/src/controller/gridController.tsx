import GridModel from '../model/gridModel';
import RestController from './restController';

export default class GridController {

  private static readonly Path = 'grid';

  private readonly controller: RestController;

  constructor(controller: RestController) {
    this.controller = controller;
  }

  solve(grid: GridModel, callback: (grid: GridModel) => void, errorCallback: (e: Error) => void) {
    const body = JSON.stringify(grid);
    this.controller.post(GridController.Path, body, callback, errorCallback);
  }
}