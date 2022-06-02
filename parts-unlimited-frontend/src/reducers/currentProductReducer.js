import { SELECTED_PRODUCT } from '../actions/Types';

export default function(state = null, action) {

    switch (action.type) {
        case SELECTED_PRODUCT:
            return action.payload;
        default:
            return state;
    }
}
