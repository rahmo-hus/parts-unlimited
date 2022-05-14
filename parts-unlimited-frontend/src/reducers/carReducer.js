import {DELETE_CAR, FETCH_CARS, SAVE_CAR, SAVE_CAR_FAIL, SAVE_CAR_SUCCESS} from '../actions/Types';

export default function(state = [], action) {
    switch (action.type) {
        case FETCH_CARS:
            return action.payload;
        case SAVE_CAR_SUCCESS:
            return {};
        case SAVE_CAR_FAIL:
            return action.payload;
        case DELETE_CAR:
            return {}
        default:
            return state;
    }
}
