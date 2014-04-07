
.z-combobox {
    display: inline-block;
    min-height: 24px;
    white-space: nowrap;
}
.z-combobox input.z-combobox-input {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    background: none repeat scroll 0 0 #FFFFFF;
    border-color: #CFCFCF -moz-use-text-color #CFCFCF #CFCFCF;
    border-image: none;
    border-radius: 3px 0 0 3px;
    border-style: solid none solid solid;
    border-width: 1px 0 1px 1px;
    color: #000000;
    font-family: Arial,Sans-serif;
    font-size: 12px;
    font-weight: normal;
    height: 24px;
    line-height: 14px;
    margin: 0;
    padding: 4px 5px;
}
.z-combobox input.z-combobox-button {
    background: none repeat scroll 0 0 #FFFFFF;
    border: 1px solid #CFCFCF;
    border-radius: 0 3px 3px 0;
    color: #636363;
    cursor: pointer;
    display: inline-block;
    font-size: 14px;
    font-weight: normal;
    height: 24px;
    line-height: 14px;
    min-width: 24px;
    overflow: hidden;
    padding: 4px;
    text-align: left;
    vertical-align: middle;
}
.z-combobox input.z-combobox-button:hover {
    background: -moz-linear-gradient(center top , #F2F9FE 0%, #D6F0FD 100%) repeat scroll 0 0 transparent;
    border-color: #8FB9D0;
}
.z-combobox input.z-combobox-button:active {
    background: -moz-linear-gradient(center top , #C3F5FE 0%, #86E2F9 100%) repeat scroll 0 0 transparent;
    border-color: #499EB3;
    box-shadow: 1px 1px 1px #91AAB7 inset;
}
input[disabled] + .z-combobox-button:hover {
    background: none repeat scroll 0 center transparent;
    border-color: #CFCFCF;
    box-shadow: none;
}
.z-combobox input.z-combobox-button {
    display: none;
}




.z-combobox input.z-combobox-input:focus {
    box-shadow: 1px 1px 1px rgba(210, 210, 210, 0.75) inset;
}
.z-combobox input.z-combobox-input[readonly] {
    background: none repeat scroll 0 0 #FAFAFA;
    border-color: #E6E6E6;
}
.z-combobox input.z-combobox-input[readonly]:focus {
    box-shadow: none;
}
.z-combobox-invalid {
    background: none repeat scroll 0 0 #FFFFFF;
    border-bottom-color: #DD7777;
    border-left-color: #DD7777;
    border-right: 1px solid #DD7777 !important;
    border-top-color: #DD7777;
    margin-right: -1px;
}
.z-combobox input.z-combobox-button {
    font-size: 16px;
    max-height: 24px;
    padding-left: 6px;
}

.z-combobox input.z-comboitem, .z-combobox input.z-comboitem-button {
    cursor: pointer;
    font-size: 12px;
    white-space: nowrap;
}
.z-combobox input.z-comboitem {
    display: block;
    padding: 0 2px;
    position: relative;
    text-shadow: 0 1px #FFFFFF;
}
.z-combobox input.z-comboitem-inner, .z-combobox input.z-comboitem-content {
    color: #555555;
    font-size: 10px;
    padding-left: 6px;
}
.z-combobox input.z-comboitem, .z-combobox input.z-comboitem a, .z-combobox input.z-comboitem a:visited {
    color: #000000;
    font-size: 12px;
    font-weight: normal;
    text-decoration: none;
}
.z-combobox input.z-comboitem-selected {
    background: -moz-linear-gradient(center top , #E5F4FB 0%, #D3EDFA 100%) repeat scroll 0 0 transparent;
}
.z-combobox input.z-comboitem:hover {
    background: -moz-linear-gradient(center top , #F2F9FE 0%, #D6F0FD 100%) repeat scroll 0 0 transparent;
}
.z-combobox input.z-comboitem-selected:hover {
    background: -moz-linear-gradient(center top , #CFEBF8 0%, #A5DAF5 100%) repeat scroll 0 0 transparent;
}
.z-combobox input.z-comboitem-image {
    margin-right: 3px;
    width: 1px;
}
.z-combobox input.z-comboitem-image:empty {
    margin-right: 0;
}
