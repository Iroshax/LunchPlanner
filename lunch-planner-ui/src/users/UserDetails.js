import React, { useEffect, useState } from 'react';
import axios from "axios";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';
import { useTable, usePagination } from 'react-table';
import NavBar from '../common/NavBar';

/** 
* Fetch all session details.
*/
export default function UserDetails() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    loadUsers();
  }, []);

  const loadUsers = () => {
    axios.get('http://localhost:8087/api/lunchPlanner/user')
      .then(response => setUsers(response.data))
      .catch(error => {
        console.error('Error fetching user list:', error);
        toast.error(`${error.response.data.Message}`);
      });
  };

  const data = React.useMemo(() => users, [users]);

  const columns = React.useMemo(() => [
    {
      Header: '#',
      accessor: (row, i) => i + 1,
    },
    {
      Header: 'First Name',
      accessor: 'firstName',
    },
    {
      Header: 'Last Name',
      accessor: 'lastName',
    },
    {
      Header: 'Username',
      accessor: 'userName',
    },
    {
      Header: 'Team',
      accessor: 'teamName',
    },
    {
      Header: 'Email Address',
      accessor: 'email',
    },
  ], []);

  const {
    getTableProps,getTableBodyProps,headerGroups,prepareRow,
    page,canPreviousPage,canNextPage,nextPage,previousPage,
  } = useTable(
    {
      columns,
      data,
      initialState: { pageIndex: 0 },
    },
    usePagination
  );

  return (
    <div>
      <NavBar></NavBar>
      <div className='container'>&nbsp;
      <ToastContainer position="top-center"/>
      <h4>User Details</h4>
      <div className='py-4'>
        <table {...getTableProps()} className="table border shadow">
          <thead>
            {headerGroups.map(headerGroup => (
              <tr {...headerGroup.getHeaderGroupProps()}>
                {headerGroup.headers.map(column => (
                  <th {...column.getHeaderProps()}>{column.render('Header')}</th>
                ))}
              </tr>
            ))}
          </thead>
          <tbody {...getTableBodyProps()}>
            {page.map((row, i) => {
              prepareRow(row);
              return (
                <tr {...row.getRowProps()} className={(row.original.sessionStatus === 'Open' ? "table-success" : '')}>
                  {row.cells.map(cell => (
                    <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                  ))}
                </tr>
              );
            })}
          </tbody>
        </table>
        <div>
          <button onClick={() => previousPage()} disabled={!canPreviousPage}>
            {'<'}
          </button>{' '}
          <button onClick={() => nextPage()} disabled={!canNextPage}>
            {'>'}
          </button>
        </div>
      </div>
    </div>
    </div>
  );
}