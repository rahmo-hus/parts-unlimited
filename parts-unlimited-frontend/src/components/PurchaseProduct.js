import React, {useState} from "react";
import "react-credit-cards/es/styles-compiled.css";
import {connect} from "react-redux";
import {purchase} from "../actions/CreditCard";

const CreditCard = (props) => {
    const [number, SetNumber] = useState("");
    const [name, SetName] = useState("");
    const [month, SetMonth] = useState("");
    let [expiry, SetExpiry] = useState("");
    const [cvc, SetCvc] = useState("");
    const [focus, SetFocus] = useState("");

    const [loading, setLoading] = useState(false);

    const handleDate = (e) => {
        SetMonth(e.target.value);
        SetExpiry(e.target.value);
    };
    const handleExpiry = (e) => {
        SetExpiry(month.concat(e.target.value));
    };

    const onSubmit = () => {
        const productIds = [];
        setLoading(true);
        for (var data of props.basketProducts) {
            productIds.push({id: data.product.id, quantity: data.quantity});
        }
        const firstName = name.split(' ')[0];
        const lastName = name.split(' ')[1];

        console.log(productIds)

        props.purchase({
            number,
            firstName,
            lastName,
            expirationDate: expiry,
            cvv: cvc
        }, productIds).then(res => {
            setLoading(res.data);
        });
    }

    return (
        <div className="wrap-body-inner">
            {/* <div className="rccs__card backcolor"> */}

            <section className="m-t-lg-30 m-t-xs-0 m-b-lg-50">
                <div className="bg-gray-f5 bg1-gray-15 p-lg-30 p-xs-15">
                    <div className="m-b-lg-10">
                        <div className="heading-1">
                            <h3 style={{textAlign: 'center'}}>Credit card info</h3>
                        </div>
                        <form>
                            <div className="row">
                                <div className="col-sm-11">
                                    <label htmlFor="name">Card Number</label>
                                    <input
                                        type="tel"
                                        value={number}
                                        name="number"
                                        className="form-control form-item"
                                        placeholder="1234 5678 9012 3456"
                                        maxlength="16"
                                        pattern="[0-9]+"
                                        onChange={(e) => {
                                            SetNumber(e.target.value);
                                        }}
                                        onFocus={(e) => SetFocus(e.target.name)}
                                    ></input>
                                </div>
                            </div>
                            <br/>
                            <div className="row">
                                <div className="col-sm-11">
                                    <label for="name">Card Name</label>
                                    <input
                                        type="text"
                                        className="form-control form-item"
                                        placeholder="John Doe"
                                        value={name}
                                        name="name"
                                        onChange={(e) => {
                                            SetName(e.target.value);
                                        }}
                                        onFocus={(e) => SetFocus(e.target.name)}
                                    ></input>
                                </div>
                            </div>
                            <br/>
                            <div className="row">
                                <div
                                    className="col=sm-8"
                                    style={{
                                        ...{"padding-right": "12em"},
                                        ...{"padding-left": "1em"}
                                    }}
                                >
                                    <label for="month">Expiration Date</label>
                                </div>
                            </div>

                            <div className="row">
                                <div className="col-sm-4">
                                    <select
                                        className="form-control form-item"
                                        name="expiry"
                                        onChange={handleDate}
                                    >
                                        <option value=" ">Month</option>
                                        <option value="01">Jan</option>
                                        <option value="02">Feb</option>
                                        <option value="03">Mar</option>
                                        <option value="04">April</option>
                                        <option value="05">May</option>
                                        <option value="06">June</option>
                                        <option value="07">July</option>
                                        <option value="08">Aug</option>
                                        <option value="09">Sep</option>
                                        <option value="10">Oct</option>
                                        <option value="11">Nov</option>
                                        <option value="12">Dec</option>
                                    </select>
                                </div>
                                &nbsp;
                                <div className="col-sm-4">
                                    <select
                                        className="form-control form-item"
                                        name="expiry"
                                        onChange={handleExpiry}
                                    >
                                        <option value=" ">Year</option>
                                        <option value="21">2021</option>
                                        <option value="22">2022</option>
                                        <option value="23">2023</option>
                                        <option value="24">2024</option>
                                        <option value="25">2025</option>
                                        <option value="26">2026</option>
                                        <option value="27">2027</option>
                                        <option value="28">2028</option>
                                        <option value="29">2029</option>
                                        <option value="30">2030</option>
                                    </select>
                                </div>
                                <div className="col-sm-3">
                                    <input
                                        type="tel"
                                        name="cvv"
                                        placeholder="CVV"
                                        maxlength="4"
                                        className=" form-control card form-item"
                                        value={cvc}
                                        pattern="\d*"
                                        onChange={(e) => {
                                            SetCvc(e.target.value);
                                        }}
                                        onFocus={(e) => SetFocus(e.target.name)}
                                    ></input>
                                </div>
                            </div>
                        </form>
                        <br/>
                        <div style={{paddingRight: 87}}>
                            {
                                props.withdrawal && (props.withdrawal === true ?
                                <div className="alert alert-success">Withdrawal success</div> :
                                    <div className="alert alert-warning">Withdrawal failed</div>)
                            }
                            <button
                                type="submit"
                                className="btn btn-secondary form-control ht-btn ht-btn-default m-b-lg-15 p-b-lg-35"
                                onClick={onSubmit}
                                disabled={loading}
                                value="Submit"
                            >Submit
                            </button>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
};

function mapStateToProps({withdrawal, basketProducts}) {
    return {withdrawal, basketProducts};
}

export default connect(mapStateToProps, {purchase})(CreditCard);
