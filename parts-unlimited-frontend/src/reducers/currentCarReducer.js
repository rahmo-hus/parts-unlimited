import { SELECTED_CAR } from '../actions/Types';

export default function(state = [], action) {  
  switch (action.type) {
    case SELECTED_CAR:
      return action.payload;
    default:
      return state;
  }
}
