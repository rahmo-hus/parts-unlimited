import React, {useEffect} from "react";
import {connect} from "react-redux";
import {fetchTransactions, findAllTransactionsByUserId} from "../actions/Transaction";
import {whoAmI} from "../actions/Auth";

const PurchaseHistory = (props) => {

    const user = JSON.parse(localStorage.getItem("user"));
    useEffect(() => {
        if (user.roles[0] === 'ROLE_CUSTOMER') {
            props.findAllTransactionsByUserId(props.user.id);
        } else {
            props.fetchTransactions();
        }
    }, []);

    const {transactions} = props;

    return (
        <div className="wrap-body-inner">
            <section className="m-t-lg-30 m-t-xs-0 m-b-lg-50">
                <div className="bg-gray-f5 bg1-gray-15 p-lg-30 p-xs-15">
                    <div className="m-b-lg-10">
                        <div className="heading-1">
                            <h3 style={{textAlign: 'center'}}>Shopping history</h3>
                        </div>
                    </div>
                    {
                        transactions && transactions.map((transaction, key) => {
                            return (
                                <div className="product-item">
                                    <div className="row m-lg-0">
                                        <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 p-l-lg-0">
                                            <a className="product-img">
                                                {transaction.parts[0].image && <img src={transaction.parts[0].image}/>}
                                            </a>
                                        </div>
                                        <div className="col-xs-7 col-sm-7 col-md-7 col-lg-7 p-lg-0">
                                            <div className="product-caption text-center">
                                                <h3 className="p-lg-0">
                                                    <a>{transaction.parts[0].name}</a>
                                                </h3>
                                                <p>
                                                    Quantity: {transaction.quantity}
                                                </p>
                                                <p>
                                                    Amount: <strong>${transaction.amount}</strong>
                                                </p>
                                                <p>
                                                    Date purchased: <strong>{transaction.date.split('T')[0]}</strong>
                                                </p>
                                                <p>
                                                    Category: <strong>{transaction.parts[0].category}</strong>
                                                </p>
                                                {
                                                    user.roles[0] === 'ROLE_SALES' &&
                                                    <p>Bought by:  <strong>{transaction.user.username}</strong></p>
                                                }
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            )
                        })
                    }
                </div>
            </section>
        </div>
    )
}

function mapStateToProps({transactions, authentication}) {
    console.log(transactions)
    return {transactions, user: authentication.user};
}

export default connect(mapStateToProps, {findAllTransactionsByUserId, whoAmI, fetchTransactions})(PurchaseHistory);
