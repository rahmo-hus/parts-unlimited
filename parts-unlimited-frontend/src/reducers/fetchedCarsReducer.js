import { FETCH_FILTERED_CARS } from '../actions/Types';

export default function(state= [], action){
    switch(action.type){
        case FETCH_FILTERED_CARS :
            return action.payload;
        default : 
        return state;
    }
}
