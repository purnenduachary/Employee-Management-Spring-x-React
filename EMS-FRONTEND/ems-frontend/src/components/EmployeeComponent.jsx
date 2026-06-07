import React from 'react'
import { useState, useEffect } from 'react'
import {createEmployee, getEmployeeById,updateEmployee} from '../services/EmployeeService'
import {useNavigate,useParams} from 'react-router-dom';

const EmployeeComponent = () => {

   const [firstName, setFirstName] = useState('')
   const [lastName, setLastName] = useState('')
   const [email, setEmail] = useState('')


   const {id} = useParams();

   const navigator = useNavigate();

   //get employee by id and set form values
   useEffect(() => {
    if(id){
        getEmployeeById(id)
            .then((response) => {
                setFirstName(response.data.firstName);
                setLastName(response.data.lastName);
                setEmail(response.data.email);
            })
            .catch(error => {
                console.log(error);
            })
    }
}, [id]);


   const [errors, setErrors] = useState({
    firstName: '',
    lastName: '',
    email: ''
   })

   function validateForm() {
    let formIsValid = true;
    const errorsCopy = { ...errors };

    if (firstName.trim()) {
      errorsCopy.firstName = '';
    } else {
      errorsCopy.firstName = 'First name is required';
      formIsValid = false;
    }

    if (lastName.trim()) {
      errorsCopy.lastName = '';
    } else {
      errorsCopy.lastName = 'Last name is required';
      formIsValid = false;
    }

    if (email.trim()) {
      errorsCopy.email = '';
    } else {
      errorsCopy.email = 'Email is required';
      formIsValid = false;
    }

    setErrors(errorsCopy);
    return formIsValid;

   }

   

    // Handle form submission
    function saveOrUpdateEmployee(event){
    event.preventDefault();

    if(validateForm()){
        const employee = {firstName, lastName, email};
        console.log("Employee Saved Successfully", employee);

        if(id){
            updateEmployee(id, employee)
                .then((response) => {
                    console.log(response.data);
                    navigator('/employees');
                })
                .catch(error => {
                    console.log(error);
                })
        }
        else{
            createEmployee(employee).then((response) => {
        console.log(response.data);
        navigator('/employees');
            }).catch((error) => {
            console.log(error);
        });
        }
    }
}

function pageTitle(id){
    if(id){
        return <h2 className='text-center'>Update Employee</h2>
    }
    else{
        return <h2 className='text-center'>Add Employee</h2>
    }
}
  


  return (
    <div className="container">
        <br/>     <br/>    <br/>
        <div className="row">
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                {
                    pageTitle(id)
                }
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>First Name:</label>
                            <input
                                type='text'
                                placeholder='Enter first name'
                                name='firstName'
                                value={firstName}
                                className={'form-control' + (errors.firstName ? ' is-invalid' : '')}
                                onChange={(event) => {setFirstName(event.target.value)}}
                                >   
                                </input>
                                {errors.firstName && <div className="invalid-feedback">{errors.firstName}</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Last Name:</label>
                            <input
                                type='text'
                                placeholder='Enter Last name'
                                name='lastName'
                                value={lastName}
                                className={'form-control' + (errors.lastName ? ' is-invalid' : '')}
                                onChange={(event) => {setLastName(event.target.value)}}
                            >
                                </input>
                                {errors.lastName && <div className = "invalid-feedback">{errors.lastName}</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Email:</label>
                            <input
                                type='text'
                                placeholder='Enter email'
                                name='email'
                                value={email}
                                className={'form-control' + (errors.email ? ' is-invalid' : '')}
                                onChange={(event) => {setEmail(event.target.value)}}
                            />
                            {errors.email && <div className="invalid-feedback">{errors.email}</div>}
                        </div>

                        <button className='btn btn-success' onClick={saveOrUpdateEmployee}>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}

export default EmployeeComponent