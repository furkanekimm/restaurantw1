import React, {Component} from 'react';
import InfoServices from "../services/InfoServices";
import HeaderComponent from "./HeaderComponent";

class ListInfoComponent extends Component {
    constructor(props) {
        super(props)
        this.state= {
            info: []
        }
    }
   componentDidMount() {
        if(localStorage.getItem("username")==null ||localStorage.getItem("password")==null){
            this.props.history.push('/')
        }
        InfoServices.getInfo().then((res)=>{
            this.setState({info:res.data})
            console.log(res.data);
        })
       InfoServices.getInfoProd().then((res)=>{
           this.setState({info:res.data})
           console.log(res.data);
       })
   }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container" style={{marginTop: "15px"}}>
                    <div className="row">
                        <table className="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th>KEY</th>
                                <th>VALUE</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.info.map(
                                    info =>
                                        <tr>
                                            <td>{info.key}</td>
                                            <td>{info.value}</td>
                                        </tr>
                                )
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        );
    }
}

export default ListInfoComponent;