import React, {useEffect, useState} from "react";
import {connect} from "react-redux";
import {fetchCarNames} from "../actions/Car";
import {saveProduct} from "../actions/Product";

const SellAPart = props => {

    const [serial, setSerial] = useState('');
    const [productionDate, setProductionDate] = useState('');
    const [brand, setBrand] = useState('');
    const [code, setCode] = useState('');
    const [manufacturer, setManufacturer] = useState('');
    const [name, setName] = useState('');
    const [type, setType] = useState('');
    const [price, setPrice] = useState('');
    const [description, setDescription] = useState('');
    const [category, setCategory] = useState('');
    const [image, setImage] = useState(null);
    const [selectedCar, setSelectedCar] = useState(null);

    const onFileChange = e => {
        getBase64(e.target.files[0]).then(res=>{
            setImage(res);
        });
    }

    useEffect(() =>{
        props.fetchCarNames();
    },[]);

    const getBase64 = (file) => {
        return new Promise(resolve => {
            let fileInfo;
            let baseURL = "";
            // Make new FileReader
            let reader = new FileReader();

            reader.readAsDataURL(file);

            reader.onload = () => {
                // Make a fileInfo Object
                console.log("Called", reader);
                baseURL = reader.result;
                resolve(baseURL);
            };
        });
    }

    const onSubmit = () =>{
        const cars = [];
        cars.push(selectedCar.id);

        props.saveProduct({
            serial,
            productionDate,
            brand,
            code,
            manufacturer,
            name,
            type,
            image,
            price,
            description,
            category,
            carIds:cars
        });
    }

    return (
        <div className="wrap-body-inner">
            <div className="hidden-xs">
                <div className="row">
                    <div className="col-lg-6">
                        <ul className="ht-breadcrumb pull-left">
                            <li className="home-act"><a><i className="fa fa-home"></i></a></li>
                            <li className="home-act"><a>Part</a></li>
                            <li className="active">Sell a part</li>
                        </ul>
                    </div>
                </div>
            </div>
            <section className="m-t-lg-30 m-t-xs-0 m-b-lg-50">
                <div>
                    <div className="row">
                        <div className="col-md-9 col-lg-9">
                            <div className="bg-gray-f5 bg1-gray-15 p-lg-30 p-xs-15">
                                <div className="m-b-lg-10">
                                    <div className="heading-1">
                                        <h3>Part Information</h3>
                                    </div>
                                    <div className="row">
                                        <div className="col-sm-12 col-md-12 col-lg-12">
                                            <div className="form-group">
                                                <input type="email" className="form-control form-item"
                                                       placeholder="Title" onChange={e => setName(e.target.value)}/>
                                                <p className="color1-8 m-t-lg-5 f-14">Maximum 100 characters</p>
                                            </div>
                                        </div>
                                        <div className="col-md-12 col-lg-12">
                                            <div className="form-group">
                                                <input type="email" className="form-control form-item"
                                                       placeholder="Price" onChange={e => setPrice(e.target.value)}/>
                                            </div>
                                        </div>
                                        <div className="col-md-12 col-lg-12">
                                            <div className="form-group">
                                                <textarea className="form-control h-200 form-item m-b-lg-5"
                                                          placeholder="Description" rows="3"
                                                          onChange={e => setDescription(e.target.value)}></textarea>
                                                <p className="color1-8 m-t-lg-5 f-14">Maximum 500 characters</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="m-b-lg-40">
                                    <div className="heading-1">
                                        <h3>Upload Images</h3>
                                    </div>
                                    {
                                        image && <p className="m-b-lg-20">1 image selected</p>
                                    }
                                    <div className="row">
                                        <div className="col-sm-4 col-md-4 col-lg-3 m-b-lg-20 text-left">
                                            <img src="images/b-img-1.jpg" alt=""/>
                                            <label htmlFor="file-upload"
                                                   className="placeholder-img choose-file-upload ">
                                                <i className="fa fa-link m-r-lg-5"></i>Choose files
                                                <input id="file-upload" type="file" className="display-none"
                                                       accept='image/*' onChange={onFileChange}/>
                                            </label>
                                            <i className="remove-img fa fa-remove"></i>
                                        </div>
                                    </div>
                                </div>
                                <div className="m-b-lg-20">
                                    <div className="heading-1">
                                        <h3>Part Details </h3>
                                    </div>
                                    <p className="m-b-lg-20">Select details for your car</p>
                                    <div className="row">
                                        <div className="col-sm-6 col-md-4 col-lg-4 m-b-lg-20">
                                            <div className="select-wrapper">
                                                <div className="dropdown">
                                                    <button className="dropdown-toggle form-item" type="button"
                                                            id="dropdownMenu1" data-toggle="dropdown"
                                                            aria-haspopup="true" aria-expanded="true">
                                                        {category === '' ? "Category" : category}
                                                    </button>
                                                    <ul className="dropdown-menu" aria-labelledby="dropdownMenu1">
                                                        <li onClick={() => setCategory('TYRES & WHEELS')}>TYRES &
                                                            WHEELS
                                                        </li>
                                                        <li onClick={() => setCategory('BRAKES DISCS')}>BRAKES DISCS
                                                        </li>
                                                        <li onClick={() => setCategory('EXHAUST TIPS')}>EXHAUST TIPS
                                                        </li>
                                                        <li onClick={() => setCategory('CAR COVERS')}>CAR COVERS</li>
                                                        <li onClick={() => setCategory('SEAT COVERS')}>SEAT COVERS</li>
                                                        <li onClick={() => setCategory('MIRRORS')}>MIRRORS</li>
                                                        <li onClick={() => setCategory('BUMPERS')}>BUMPERS</li>
                                                        <li onClick={() => setCategory('FLOOR MATS')}>FLOOR MATS</li>
                                                        <li onClick={() => setCategory('CALIPERS')}>CALIPERS</li>
                                                        <li onClick={() => setCategory('HAND BRAKES')}>HAND BRAKES</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="col-sm-6 col-md-4 col-lg-4 m-b-lg-20">
                                            <input type="email" className="form-control form-item" placeholder="Code"
                                                   onChange={e => setCode(e.target.value)}/>
                                        </div>
                                        <div className="col-sm-6 col-md-4 col-lg-4 m-b-lg-20">
                                            <input type="email" className="form-control form-item" placeholder="Serial"
                                                   onChange={e => setSerial(e.target.value)}/>
                                        </div>
                                        <div className="col-sm-6 col-md-4 col-lg-4 m-b-lg-20">
                                            <input type="date" className="form-control form-item" placeholder="Date"
                                                   onChange={e => setProductionDate(e.target.value)}/>
                                        </div>
                                        <div className="col-sm-6 col-md-4 col-lg-4 m-b-lg-20">
                                            <div className="select-wrapper">
                                                <div className="dropdown">
                                                    <button className="dropdown-toggle form-item" type="button"
                                                            id="dropdownMenu2" data-toggle="dropdown"
                                                            aria-haspopup="true" aria-expanded="true">
                                                        {brand === '' ? "Brand" : brand}
                                                    </button>
                                                    <ul className="dropdown-menu" aria-labelledby="dropdownMenu2">
                                                        <li onClick={() => setBrand('American Racing')}>American
                                                            Racing
                                                        </li>
                                                        <li onClick={() => setBrand('XD Series')}>XD Series</li>
                                                        <li onClick={() => setBrand('Helo')}>Helo</li>
                                                        <li onClick={() => setBrand('KMC')}>KMC</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="col-sm-6 col-md-4 col-lg-4 m-b-lg-20">
                                            <div className="select-wrapper">
                                                <div className="dropdown">
                                                    <button className="dropdown-toggle form-item" type="button"
                                                            id="dropdownMenu2" data-toggle="dropdown"
                                                            aria-haspopup="true" aria-expanded="true">
                                                        {selectedCar === null? "Car" : selectedCar.name}
                                                    </button>
                                                    <ul className="dropdown-menu" aria-labelledby="dropdownMenu2">
                                                        {
                                                            props.carNames && props.carNames.map((car, key)=>{
                                                                return <li onClick={()=>setSelectedCar(car)} value={car.name}>{car.name}</li>
                                                            })
                                                        }
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="col-sm-6 col-md-4 col-lg-4 m-b-lg-20">
                                            <div className="select-wrapper">
                                                <div className="dropdown">
                                                    <button className="dropdown-toggle form-item" type="button"
                                                            id="dropdownMenu5" data-toggle="dropdown"
                                                            aria-haspopup="true" aria-expanded="true">
                                                        {manufacturer === '' ? "Manufacturer" : manufacturer}
                                                    </button>
                                                    <ul className="dropdown-menu" aria-labelledby="dropdownMenu5">
                                                        <li onClick={() => setManufacturer('China')}>China</li>
                                                        <li onClick={() => setManufacturer('USA')}>USA</li>
                                                        <li onClick={() => setManufacturer('Japan')}>Japan</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="m-b-lg-20">
                                    <div className="col-lg-4 m-t-lg-10">
                                        <button type="submit" onClick={onSubmit} className="ht-btn ht-btn-default">
                                            Submit
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-3">
                            <div className="m-t-xs-30">
                                <div className="heading-1">
                                    <h3>Trouble Uploading?</h3>
                                </div>
                                <p>
                                    Try do something
                                </p>
                                <ul className="list-default">
                                    <li><a><i className="fa fa-angle-right"></i>Lorem ipsum dolor sit amet</a></li>
                                    <li><a><i className="fa fa-angle-right"></i>Lorem ipsum dolor sit amet</a></li>
                                    <li><a><i className="fa fa-angle-right"></i>Lorem ipsum dolor sit amet</a></li>
                                    <li><a><i className="fa fa-angle-right"></i>Lorem ipsum dolor sit amet</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    )
}

function mapStateToProps({carNames, success}) {
    return {carNames, success};
}

export default connect(mapStateToProps, {fetchCarNames, saveProduct})(SellAPart);
