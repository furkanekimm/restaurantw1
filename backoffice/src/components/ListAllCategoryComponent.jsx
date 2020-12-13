import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import ProductService from "../services/ProductService";
import {UserContext} from "../context"
import Loader from 'react-loader-spinner';

class ListAllCategoryComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            categories :[],
            loading:false
        }
        this.editCategory = this.editCategory.bind(this);
        this.deleteCategory = this.deleteCategory.bind(this);
    }

    editCategory(category){
        this.props.history.push({
            pathname:'/updatecategory',
            state:{
                category:category
            }
        });
    }

    deleteCategory(id){
        ProductService.deleteCategory(id).then(res=>{
            window.location.reload();
        });
    }

    componentDidMount(){
        console.log(this.context);
        if(localStorage.getItem("username") ==null && localStorage.getItem("password") == null ){
            this.props.history.push('/');
        }
        this.state.loading=false;
        ProductService.listCategories().then(res =>{
            this.setState({categories:res.data,loading:true})
        });

    }

    addCategory=(e)=>{
        this.props.history.push('/addcategory');
    }


    render() {
        const {loading} = this.state
        return (

            <div>
                {!loading && <div style={{
                    width: '100%',
                    height: "100",
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    marginTop: '28%'
                }}>
                    <Loader type="TailSpin" color="#000" height="50" width="100"/>
                </div> ||
                <div>
                    <HeaderComponent/>
                    <div className="container">
                    <div className="row">
                    <button style={{
                    marginTop: "15px",
                    marginBottom: "10px"
                }} className="btn btn-primary"onClick={this.addCategory} >Add Category</button>
                    </div>
                    <div className="row">
                    <table className="table table-striped table-bordered">
                    <thead>
                    <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>ImageUrl</th>
                    <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.categories.map(
                            category =>
                                <tr key={category.id}>
                                    <td>{category.name}</td>
                                    <td>{category.description}</td>
                                    <td><img
                                        style={{marginLeft: "0rem", marginRight: "10px"}}
                                        src={'data:image/png;base64,' + category.fileContent}
                                        width="45rem" height="39rem"
                                    /></td>
                                    <td>
                                        <button
                                            onClick={() => this.editCategory(category)}
                                            className="btn btn-info"> Update
                                        </button>
                                        <button style={{marginLeft: "6px"}}
                                                onClick={() => this.deleteCategory(category.id)}
                                                className="btn btn-danger"> Delete
                                        </button>
                                    </td>
                                </tr>
                        )
                    }
                    </tbody>
                    </table>
                    </div>

                    </div>
                </div>
                }
            </div>
        );
    }
}
ListAllCategoryComponent.contextType=UserContext;
export default ListAllCategoryComponent;