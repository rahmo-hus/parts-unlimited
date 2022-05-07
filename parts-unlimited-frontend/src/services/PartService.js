import axios from 'axios';
import authHeader from "./AuthHeader";
const API_URL = 'http://localhost:9090/api/parts/'

class PartService{
    getAllParts(){
        return axios.get(API_URL+'/get-all-parts', {headers: authHeader()});
    }

    getPartBySerial(serial){
        return axios.get(API_URL+'/get-part/'+serial, {headers: authHeader()});
    }

    getPartsByDate(date){
        return axios.get(API_URL+'/get-parts/'+date, {headers: authHeader()});
    }

    getByBrandAndCarName(brand, car){
        return axios.get(API_URL+'/get-part-by/'+brand+'/'+car, {headers: authHeader()});
    }

    deletePartById(id){
        return axios.delete(API_URL+'/delete-part/'+id, {headers: authHeader()});
    }
}

export default new PartService();
