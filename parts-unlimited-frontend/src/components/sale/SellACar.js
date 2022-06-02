import React, {useEffect, useState} from 'react';
import {connect} from "react-redux";
import {fetchBrands} from "../../actions/Brand";
import {saveCar} from "../../actions/Car";

const Sell = (props) => {
    const [name, setName] = useState('');
    const [rating, setRating] = useState('');
    const [price, setPrice] = useState('');
    const [description, setDescription] = useState('');
    const [body, setBody] = useState('');
    const [year, setYear] = useState(1900);
    const [brand, setBrand] = useState(null);
    const [condition, setCondition] = useState('');
    const [model, setModel] = useState('');
    const [fuel, setFuel] = useState('');
    const [transmission, setTransmission] = useState('');
    const [color, setColor] = useState('');
    const [door, setDoor] = useState(0);
    const [mileage, setMileage] = useState('');
    const [images, setImages] = useState([]);
    const [features, setFeatures] = useState([]);

    const onFileChange = e => {
        for(var f of e.target.files){
            getBase64(f).then(res=>{
                images.push(res);
            });
        }
    }

    const getBase64 = (file) =>{
        return new Promise(resolve => {
            let fileInfo;
            let baseURL = "";
            // Make new FileReader
            let reader = new FileReader();

            reader.readAsDataURL(file);

            reader.onload = () => {
                baseURL = reader.result;
                resolve(baseURL);
            };
        });
    }

    const onSubmit = e =>{
        e.preventDefault();
        props.saveCar({
            name,
            price,
            description,
            body,
            year,
            brandId: brand.id,
            condition,
            model,
            fuel,
            transmission,
            color,
            door,
            mileage,
            images,
            features
        });
    }

    useEffect(() => {
        props.fetchBrands();
    }, []);

    return (
        <div className="wrap-body-inner">
            <div className="hidden-xs">
                <div className="row">
                    <div className="col-lg-6">
                        <ul className="ht-breadcrumb pull-left">
                            <li className="home-act"><a><i className="fa fa-home"></i></a></li>
                            <li className="home-act"><a>Car</a></li>
                            <li className="active">Sell a car</li>
                        </ul>
                    </div>
                </div>
            </div>
            <section className="m-t-lg-30 m-t-xs-0 m-b-lg-50">
                <div>
                    <div className="row">
                        <div className="col-md-12 col-lg-9">
                            <div className="bg-gray-f5 bg1-gray-15 p-lg-30 p-xs-15">
                                <div className="m-b-lg-10">
                                    <div className="heading-1">
                                        <h3>Car Information</h3>
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
                                        images && images.length > 0 &&
                                        <p className="m-b-lg-20">{images.length} images selected</p>
                                    }
                                    <div className="row">
                                        <div className="col-sm-4 col-md-4 col-lg-3 m-b-lg-20 text-left">
                                            <img src="images/b-img-1.jpg" alt=""/>
                                            <label htmlFor="file-upload"
                                                   className="placeholder-img choose-file-upload ">
                                                <i className="fa fa-link m-r-lg-5"></i>Choose files
                                                <input id="file-upload" type="file" className="display-none" multiple
                                                       accept='image/*' onChange={onFileChange}/>
                                            </label>
                                            <i className="remove-img fa fa-remove"></i>
                                        </div>
                                    </div>
                                </div>
                                <div className="m-b-lg-20">
                                    <div className="heading-1">
                                        <h3>Car Details </h3>
                                    </div>
                                    <p className="m-b-lg-20">Select details for your car</p>
                                    <div className="row">
                                        <div className="col-sm-6 col-md-4 col-lg-4 m-b-lg-20">
                                            <div className="select-wrapper">
                                                <div className="dropdown">
                                                    <button className="dropdown-toggle form-item" type="button"
                                                            id="dropdownMenu1" data-toggle="dropdown"
                                                            aria-haspopup="true" aria-expanded="true">
                                                        {condition === '' ? "Condition" : condition}
                                                    </button>
                                                    <ul className="dropdown-menu" aria-labelledby="dropdownMenu1">
                                                        <li onClick={() => setCondition('New Car')}>New Car</li>
                                                        <li onClick={() => setCondition('Used Cars')}>Used Cars</li>
                                                        <li onClick={() => setCondition('Car Rental')}>Car Rental</li>
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
                                                        {brand === null ? "Brand" : brand.name}
                                                    </button>
                                                    <ul className="dropdown-menu" aria-labelledby="dropdownMenu2">
                                                        {
                                                            props.brands && props.brands.map((b, key) => {
                                                                return (
                                                                    <li onClick={() => setBrand(b)}>{b.name}</li>
                                                                )
                                                            })
                                                        }
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="col-sm-6 col-md-4 col-lg-4 m-b-lg-20">
                                            <input type="email" className="form-control form-item" placeholder="Model"
                                                   onChange={e => setModel(e.target.value)}/>
                                        </div>
                                        <div className="col-sm-6 col-md-4 col-lg-4 m-b-lg-20">
                                            <div className="select-wrapper">
                                                <div className="dropdown">
                                                    <button className="dropdown-toggle form-item" type="button"
                                                            id="dropdownMenu4" data-toggle="dropdown"
                                                            aria-haspopup="true" aria-expanded="true">
                                                        {year === 1900 ? "Year" : year}
                                                    </button>
                                                    <ul className="dropdown-menu" aria-labelledby="dropdownMenu4">
                                                        <li onClick={() => setYear(2022)}>2022</li>
                                                        <li onClick={() => setYear(2021)}>2021</li>
                                                        <li onClick={() => setYear(2020)}>2020</li>
                                                        <li onClick={() => setYear(2019)}>2019</li>
                                                        <li onClick={() => setYear(2018)}>2018</li>
                                                        <li onClick={() => setYear(2017)}>2017</li>
                                                        <li onClick={() => setYear(2016)}>2016</li>
                                                        <li onClick={() => setYear(2015)}>2015</li>
                                                        <li onClick={() => setYear(2014)}>2014</li>
                                                        <li onClick={() => setYear(2013)}>2013</li>
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
                                                        {body === '' ? "Body" : body}
                                                    </button>
                                                    <ul className="dropdown-menu" aria-labelledby="dropdownMenu5">
                                                        <li onClick={() => setBody('Sedan')}>Sedan</li>
                                                        <li onClick={() => setBody('SUV')}>SUV</li>
                                                        <li onClick={() => setBody('Truck')}>Truck</li>
                                                        <li onClick={() => setBody('Coupe')}>Coupe</li>
                                                        <li onClick={() => setBody('Minivan')}>Minivan</li>
                                                        <li onClick={() => setBody('Compact')}>Compact</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="col-sm-6 col-md-4 col-lg-4 m-b-lg-20">
                                            <div className="select-wrapper">
                                                <div className="dropdown">
                                                    <button className="dropdown-toggle form-item" type="button"
                                                            id="dropdownMenu6" data-toggle="dropdown"
                                                            aria-haspopup="true" aria-expanded="true">
                                                        {transmission === '' ? "Transmission" : transmission}
                                                    </button>
                                                    <ul className="dropdown-menu" aria-labelledby="dropdownMenu6">
                                                        <li onClick={() => setTransmission('Automatic')}>Automatic</li>
                                                        <li onClick={() => setTransmission('Manual')}>Manual</li>
                                                        <li onClick={() => setTransmission('Semi-automatic')}>Semi-automatic</li>
                                                        <li onClick={() => setTransmission('All')}>All</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="col-sm-6 col-md-4 col-lg-4 m-b-lg-20">
                                            <div className="select-wrapper">
                                                <div className="dropdown">
                                                    <button className="dropdown-toggle form-item" type="button"
                                                            id="dropdownMenu7" data-toggle="dropdown"
                                                            aria-haspopup="true" aria-expanded="true">
                                                        {fuel === '' ? "Fuel type" : fuel}
                                                    </button>
                                                    <ul className="dropdown-menu" aria-labelledby="dropdownMenu7">
                                                        <li onClick={() => setFuel('Hybrid')}>Hybrid</li>
                                                        <li onClick={() => setFuel('LPG')}>LPG</li>
                                                        <li onClick={() => setFuel('Gasoline')}>Gasoline</li>
                                                        <li onClick={() => setFuel('Electric')}>Electric</li>
                                                        <li onClick={() => setFuel('Diesel')}>Diesel</li>
                                                        <li onClick={() => setFuel('Ethanol')}>Ethanol</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="col-sm-6 col-md-4 col-lg-4 m-b-lg-20">
                                            <div className="select-wrapper">
                                                <div className="dropdown">
                                                    <button className="dropdown-toggle form-item" type="button"
                                                            id="dropdownMenu9" data-toggle="dropdown"
                                                            aria-haspopup="true" aria-expanded="true">
                                                        {color === '' ? "Color" : color}
                                                    </button>
                                                    <ul className="dropdown-menu" aria-labelledby="dropdownMenu9">
                                                        <li onClick={() => setColor('Red')}>Red</li>
                                                        <li onClick={() => setColor('Gray')}>Gray</li>
                                                        <li onClick={() => setColor('White')}>White</li>
                                                        <li onClick={() => setColor('Black')}>Black</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="col-sm-6 col-md-4 col-lg-4 m-b-lg-20">
                                            <div className="select-wrapper">
                                                <div className="dropdown">
                                                    <button className="dropdown-toggle form-item" type="button"
                                                            id="dropdownMenu10" data-toggle="dropdown"
                                                            aria-haspopup="true" aria-expanded="true">
                                                        {door === 0 ? "Door" : door}
                                                    </button>
                                                    <ul className="dropdown-menu" aria-labelledby="dropdownMenu10">
                                                        <li onClick={() => setDoor(2)}>2</li>
                                                        <li onClick={() => setDoor(3)}>3</li>
                                                        <li onClick={() => setDoor(4)}>4</li>
                                                        <li onClick={() => setDoor(5)}>5</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="col-sm-6 col-md-4 col-lg-4 m-b-lg-20">
                                            <div className="select-wrapper">
                                                <div className="dropdown">
                                                    <button className="dropdown-toggle form-item" type="button"
                                                            id="dropdownMenu11" data-toggle="dropdown"
                                                            aria-haspopup="true" aria-expanded="true">
                                                        {mileage === '' ? "Mileage" : mileage}
                                                    </button>
                                                    <ul className="dropdown-menu" aria-labelledby="dropdownMenu11">
                                                        <li onClick={() => setMileage('0 - 1,000')}>0 - 1,000</li>
                                                        <li onClick={() => setMileage('1,000 - 10,000')}>1,000 -
                                                            10,000
                                                        </li>
                                                        <li onClick={() => setMileage('10,000 - 20,000')}>10,000 -
                                                            20,000
                                                        </li>
                                                        <li onClick={() => setMileage('20,000 - 30,000')}>20,000 -
                                                            30,000
                                                        </li>
                                                        <li onClick={() => setMileage('30,000 - 40,000')}>30,000 -
                                                            40,000
                                                        </li>
                                                        <li onClick={() => setMileage('40,000 - 50,000')}>40,000 -
                                                            50,000
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="m-b-lg-30">
                                    <div className="heading-1">
                                        <h3>Features & Options</h3>
                                    </div>
                                    <p className="m-b-lg-20">Select additional features of given vehicle</p>
                                    <div className="row">
                                        <div className="col-lg-4 text-left">
                                            <div className="checkbox">
                                                <input type="checkbox" onChange={event => {
                                                    if (!features.find(f => f === event.target.value)) {
                                                        features.push(event.target.value);
                                                    }
                                                }} value="Auxiliary" id="check0"/>
                                                <label htmlFor="check0" className="fa fa-check"></label>
                                                Auxiliary heating
                                            </div>
                                        </div>
                                        <div className="col-lg-4 text-left">
                                            <div className="checkbox">
                                                <input type="checkbox" onChange={event => {
                                                    if (!features.find(f => f === event.target.value)) {
                                                        features.push(event.target.value);
                                                    }
                                                }} value="Head-up display" id="check1"/>
                                                <label htmlFor="check1" className="fa fa-check"></label>
                                                Head-up display
                                            </div>
                                        </div>
                                        <div className="col-lg-4 text-left">
                                            <div className="checkbox">
                                                <input type="checkbox" onChange={event => {
                                                    if (!features.find(f => f === event.target.value)) {
                                                        features.push(event.target.value);
                                                    }
                                                }} value="Alloy wheels" id="check2"/>
                                                <label htmlFor="check2" className="fa fa-check"></label>
                                                Alloy wheels
                                            </div>
                                        </div>
                                        <div className="col-lg-4 text-left">
                                            <div className="checkbox">
                                                <input type="checkbox" onChange={event => {
                                                    if (!features.find(f => f === event.target.value)) {
                                                        features.push(event.target.value);
                                                    }
                                                }} value="MP3 interface" id="check3"/>
                                                <label htmlFor="check3" className="fa fa-check"></label>
                                                MP3 interface
                                            </div>
                                        </div>
                                        <div className="col-lg-4 text-left">
                                            <div className="checkbox">
                                                <input type="checkbox" onChange={event => {
                                                    if (!features.find(f => f === event.target.value)) {
                                                        features.push(event.target.value);
                                                    }
                                                }} value="Bluetooth" id="check4"/>
                                                <label htmlFor="check4" className="fa fa-check"></label>
                                                Bluetooth
                                            </div>
                                        </div>
                                        <div className="col-lg-4 text-left">
                                            <div className="checkbox">
                                                <input type="checkbox" onChange={event => {
                                                    if (!features.find(f => f === event.target.value)) {
                                                        features.push(event.target.value);
                                                    }
                                                }} value="Electric side mirror" id="check6"/>
                                                <label htmlFor="check6" className="fa fa-check"></label>
                                                Electric side mirror
                                            </div>
                                        </div>
                                        <div className="col-lg-4 text-left">
                                            <div className="checkbox">
                                                <input type="checkbox" onChange={event => {
                                                    if (!features.find(f => f === event.target.value)) {
                                                        features.push(event.target.value);
                                                    }
                                                }} value="Navigation system" id="check7"/>
                                                <label htmlFor="check7" className="fa fa-check"></label>
                                                Navigation system
                                            </div>
                                        </div>
                                        <div className="col-lg-4 text-left">
                                            <div className="checkbox">
                                                <input type="checkbox" onChange={event => {
                                                    if (!features.find(f => f === event.target.value)) {
                                                        features.push(event.target.value);
                                                    }
                                                }} value="CD player" id="check8"/>
                                                <label htmlFor="check8" className="fa fa-check"></label>
                                                CD player
                                            </div>
                                        </div>
                                        <div className="col-lg-4 text-left">
                                            <div className="checkbox">
                                                <input type="checkbox" onChange={event => {
                                                    if (!features.find(f => f === event.target.value)) {
                                                        features.push(event.target.value);
                                                    }
                                                }} value="Panoramic roof" id="check11"/>
                                                <label htmlFor="check11" className="fa fa-check"></label>
                                                Panoramic roof
                                            </div>
                                        </div>
                                        <div className="col-lg-4 text-left">
                                            <div className="checkbox">
                                                <input type="checkbox" onChange={event => {
                                                    if (!features.find(f => f === event.target.value)) {
                                                        features.push(event.target.value);
                                                    }
                                                }} value="Central locking" id="check12"/>
                                                <label htmlFor="check12" className="fa fa-check"></label>
                                                Central locking
                                            </div>
                                        </div>
                                        <div className="col-lg-4 text-left">
                                            <div className="checkbox">
                                                <input type="checkbox" onChange={event => {
                                                    if (!features.find(f => f === event.target.value)) {
                                                        features.push(event.target.value);
                                                    }
                                                }} value="Parking sensors" id="check14"/>
                                                <label htmlFor="check14" className="fa fa-check"></label>
                                                Parking sensors
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="m-b-lg-20">
                                    <div className="col-lg-4 m-t-lg-10">
                                        <button type="submit" onClick={ onSubmit} className="ht-btn ht-btn-default">
                                            Submit
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );

};

function mapStateToProps({brands, success}) {
    return {brands, success};
}

export default connect(mapStateToProps, {fetchBrands, saveCar})(Sell);
