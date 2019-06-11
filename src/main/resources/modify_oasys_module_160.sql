-- This file is an export from Apex. It includes dev activity for the breakout web
-- link to the new tech Sentence Plan.

set define off verify off feedback off
    whenever sqlerror exit sql.sqlcode rollback
--------------------------------------------------------------------------------
--
-- ORACLE Application Express (APEX) export file
--
-- You should run the script connected to SQL*Plus as the Oracle user
-- APEX_050000 or as the owner (parsing schema) of the application.
--
-- NOTE: Calls to apex_application_install override the defaults below.
--
--------------------------------------------------------------------------------
begin
    wwv_flow_api.import_begin (
            p_version_yyyy_mm_dd=>'2013.01.01'
        ,p_release=>'5.0.4.00.12'
        ,p_default_workspace_id=>1045421411623563
        ,p_default_application_id=>160
        ,p_default_owner=>'EORAPP'
        );
end;
/
prompt --application/set_environment

prompt APPLICATION 160 - EOROFF030 - Offender Record
--
-- Application Export:
--   Application:     160
--   Name:            EOROFF030 - Offender Record
--   Date and Time:   12:37 Tuesday June 11, 2019
--   Exported By:     MIKEJACKSON
--   Flashback:       0
--   Export Type:     Application Export
--   Version:         5.0.4.00.12
--   Instance ID:     69430289613248
--

-- Application Statistics:
--   Pages:                     11
--     Items:                  221
--     Validations:              6
--     Processes:               41
--     Regions:                 42
--     Buttons:                 46
--     Dynamic Actions:         39
--   Shared Components:
--     Logic:
--       Items:                 24
--       Processes:             13
--     Navigation:
--       Breadcrumbs:            1
--       NavBar Entries:         1
--     Security:
--       Authentication:         1
--       Authorization:         23
--     User Interface:
--       Themes:                 1
--       Templates:
--         Page:                 7
--         Region:              10
--         Label:                3
--         List:                 1
--         Popup LOV:            1
--         Calendar:             1
--         Button:               7
--         Report:               2
--       Shortcuts:              1
--       Plug-ins:               1
--     Globalization:
--     Reports:
--   Supporting Objects:  Included

prompt --application/delete_application
begin
    wwv_flow_api.remove_flow(wwv_flow.g_flow_id);
end;
/
prompt --application/ui_types
begin
    null;
end;
/
prompt --application/create_application
begin
    wwv_flow_api.create_flow(
            p_id=>wwv_flow.g_flow_id
        ,p_display_id=>nvl(wwv_flow_application_install.get_application_id,160)
        ,p_owner=>nvl(wwv_flow_application_install.get_schema,'EORAPP')
        ,p_name=>nvl(wwv_flow_application_install.get_application_name,'EOROFF030 - Offender Record')
        ,p_alias=>nvl(wwv_flow_application_install.get_application_alias,'EOROFF030')
        ,p_page_view_logging=>'YES'
        ,p_page_protection_enabled_y_n=>'Y'
        ,p_checksum_salt_last_reset=>'20190611123314'
        ,p_bookmark_checksum_function=>'MD5'
        ,p_max_session_length_sec=>28800
        ,p_max_session_idle_sec=>36000
        ,p_compatibility_mode=>'4.2'
        ,p_html_escaping_mode=>'B'
        ,p_flow_language=>'en-gb'
        ,p_flow_language_derived_from=>'FLOW_PRIMARY_LANGUAGE'
        ,p_date_format=>'DD/MM/YYYY'
        ,p_flow_image_prefix => nvl(wwv_flow_application_install.get_image_prefix,'')
        ,p_documentation_banner=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '$Workfile:   OFF030.sql  $',
            '',
            '$Date:   Mar 20 2019 14:28:30  $',
            '',
            '$Revision:   1.187  $ '))
        ,p_authentication=>'PLUGIN'
        ,p_authentication_id=>wwv_flow_api.id(15975057597201081)
        ,p_logout_url=>'f?p=EORSEC040:SEC040_LANDING:&SESSION.::::P10_SESSION_EXPIRED:N'
        ,p_application_tab_set=>1
        ,p_public_user=>'APEX_PUBLIC_USER'
        ,p_proxy_server=> nvl(wwv_flow_application_install.get_proxy,'')
        ,p_flow_version=>'release 1.0'
        ,p_flow_status=>'AVAILABLE_W_EDIT_LINK'
        ,p_flow_unavailable_text=>'This application is currently unavailable at this time.'
        ,p_exact_substitutions_only=>'Y'
        ,p_browser_cache=>'N'
        ,p_browser_frame=>'D'
        ,p_deep_linking=>'Y'
        ,p_vpd=>'authorisation_pkg.vpd;'
        ,p_runtime_api_usage=>'T:O:W'
        ,p_security_scheme=>wwv_flow_api.id(5052631587350853)
        ,p_authorize_public_pages_yn=>'Y'
        ,p_rejoin_existing_sessions=>'P'
        ,p_csv_encoding=>'Y'
        ,p_error_handling_function=>'error_message_pkg.apex_error_handling'
        ,p_last_updated_by=>'MIKEJACKSON'
        ,p_last_upd_yyyymmddhh24miss=>'20190611123314'
        ,p_file_prefix => nvl(wwv_flow_application_install.get_static_app_file_prefix,'')
        ,p_ui_type_name => null
        );
end;
/
prompt --application/shared_components/navigation/lists
begin
    null;
end;
/
prompt --application/shared_components/files
begin
    null;
end;
/
prompt --application/plugin_settings
begin
    wwv_flow_api.create_plugin_setting(
            p_id=>wwv_flow_api.id(2287222184500464)
        ,p_plugin_type=>'ITEM TYPE'
        ,p_plugin=>'NATIVE_YES_NO'
        ,p_attribute_01=>'Y'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_plugin_setting(
            p_id=>wwv_flow_api.id(2324586203678268)
        ,p_plugin_type=>'REGION TYPE'
        ,p_plugin=>'NATIVE_DISPLAY_SELECTOR'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_plugin_setting(
            p_id=>wwv_flow_api.id(2324632790678268)
        ,p_plugin_type=>'REGION TYPE'
        ,p_plugin=>'NATIVE_CSS_CALENDAR'
        );
end;
/
prompt --application/shared_components/security/authorizations
begin
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(1345109290433762)
        ,p_name=>'ATZ_FNC1371'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1371'');'
        ,p_error_message=>'No LAO access'
        ,p_caching=>'BY_USER_BY_PAGE_VIEW'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(1345929469496385)
        ,p_name=>'ATZ_FNC1224'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1224'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(1347427081198872)
        ,p_name=>'ATZ_FNC4000'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC4000'');'
        ,p_error_message=>'No Create Assessment Access'
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(1361412012250723)
        ,p_name=>'ATZ_FNC1230'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1230'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(1418609108349837)
        ,p_name=>'ATZ_FNC1219'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1219'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(1418902660357454)
        ,p_name=>'ATZ_FNC1217'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1217'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(1419805664500221)
        ,p_name=>'ATZ_FNC1215'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1215'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(1420103025508904)
        ,p_name=>'ATZ_FNC1216'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1216'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(1420425534515389)
        ,p_name=>'ATZ_FNC1394'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1394'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(2777313126363603)
        ,p_name=>'ATZ_FNCCREATE_AUTO_TRANS_REQST'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return mod_trf010_pkg.p10_create_aut_transfer;'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(5052631587350853)
        ,p_name=>'ATZ_EOR'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_module_authorised(p_app_id => v (''APP_ID''));'
        ,p_error_message=>' '
        ,p_reference_id=>2287322028560609
        ,p_caching=>'BY_USER_BY_PAGE_VIEW'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(5065329904605144)
        ,p_name=>'ATZ_FNC1508'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1508'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(7169120974722037)
        ,p_name=>'ATZ_FNC1395'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'RETURN authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1395'');'
        ,p_error_message=>'error'
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(7259628131616558)
        ,p_name=>'ATZ_FNC1505'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1505'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(9177910092577585)
        ,p_name=>'ATZ_FNC1374'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1374'');'
        ,p_error_message=>'No LAO access'
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(9178424637581810)
        ,p_name=>'ATZ_FNC1373'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1373'');'
        ,p_error_message=>'No LAO access'
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(11371114710319837)
        ,p_name=>'ATZ_FNC1003'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1003'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(12230222424598635)
        ,p_name=>'ATZ_FNC1218'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1218'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(12858825827458081)
        ,p_name=>'ATZ_FNC1001'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1001TR'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(13484016745293790)
        ,p_name=>'ATZ_FNC1506'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1506'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(13484327827297047)
        ,p_name=>'ATZ_FNC1511'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1511'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(13484625318315252)
        ,p_name=>'ATZ_FNC1512'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1512'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
    wwv_flow_api.create_security_scheme(
            p_id=>wwv_flow_api.id(14994009183761854)
        ,p_name=>'ATZ_FNC1477'
        ,p_scheme_type=>'NATIVE_FUNCTION_BODY'
        ,p_attribute_01=>'return authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1477'');'
        ,p_error_message=>' '
        ,p_caching=>'BY_USER_BY_SESSION'
        );
end;
/
prompt --application/shared_components/navigation/navigation_bar
begin
    wwv_flow_api.create_icon_bar_item(
            p_id=>wwv_flow_api.id(15975331541201081)
        ,p_icon_sequence=>200
        ,p_icon_subtext=>'Logout'
        ,p_icon_target=>'&LOGOUT_URL.'
        ,p_icon_image_alt=>'Logout'
        ,p_icon_height=>32
        ,p_icon_width=>32
        ,p_icon_height2=>24
        ,p_icon_width2=>24
        ,p_nav_entry_is_feedback_yn=>'N'
        ,p_icon_bar_disp_cond_type=>'CURRENT_LOOK_IS_1'
        ,p_cell_colspan=>1
        );
end;
/
prompt --application/shared_components/logic/application_processes
begin
    wwv_flow_api.create_flow_process(
            p_id=>wwv_flow_api.id(9002276031163482)
        ,p_process_sequence=>1
        ,p_process_point=>'AFTER_BOX_BODY'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'AP_OLAR_SYS'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  app_standard_events_pkg.ap_olar;',
            'end;'))
        ,p_process_error_message=>' '
        );
    wwv_flow_api.create_flow_process(
            p_id=>wwv_flow_api.id(9002476770192151)
        ,p_process_sequence=>1
        ,p_process_point=>'AFTER_FOOTER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'AP_OLAF_SYS'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  app_standard_events_pkg.ap_olaf;',
            'end;'))
        ,p_process_error_message=>' '
        );
    wwv_flow_api.create_flow_process(
            p_id=>wwv_flow_api.id(5300718104001856)
        ,p_process_sequence=>1
        ,p_process_point=>'AFTER_HEADER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'AP_OLBH_ED_SYS'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  app_standard_events_pkg.ap_olbh_error_displayed;',
            'end;'))
        ,p_process_error_message=>' '
        ,p_process_when_type=>'DISPLAYING_INLINE_VALIDATION_ERRORS'
        );
    wwv_flow_api.create_flow_process(
            p_id=>wwv_flow_api.id(9002063910160009)
        ,p_process_sequence=>1
        ,p_process_point=>'AFTER_HEADER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'AP_OLAH_SYS'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  app_standard_events_pkg.ap_olah;',
            'end;'))
        ,p_process_error_message=>' '
        );
    wwv_flow_api.create_flow_process(
            p_id=>wwv_flow_api.id(13377233543158548)
        ,p_process_sequence=>1
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'AP_OSA_SYS'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  app_standard_events_pkg.ap_osa;',
            'end;'))
        ,p_process_error_message=>' '
        );
    wwv_flow_api.create_flow_process(
            p_id=>wwv_flow_api.id(9001883519156194)
        ,p_process_sequence=>1
        ,p_process_point=>'BEFORE_BOX_BODY'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'AP_OLBR_SYS'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  app_standard_events_pkg.ap_olbr;',
            'end;'))
        ,p_process_error_message=>' '
        );
    wwv_flow_api.create_flow_process(
            p_id=>wwv_flow_api.id(9001587197147829)
        ,p_process_sequence=>1
        ,p_process_point=>'BEFORE_FOOTER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'AP_OLBF_SYS'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  app_standard_events_pkg.ap_olbf;',
            'end;'))
        ,p_process_error_message=>' '
        );
    wwv_flow_api.create_flow_process(
            p_id=>wwv_flow_api.id(13375925465948015)
        ,p_process_sequence=>1
        ,p_process_point=>'BEFORE_HEADER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'AP_OLBH_SYS'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  app_standard_events_pkg.ap_olbh;',
            'end;'))
        ,p_process_error_message=>' '
        );
    wwv_flow_api.create_flow_process(
            p_id=>wwv_flow_api.id(2327264755686688)
        ,p_process_sequence=>1
        ,p_process_point=>'ON_DEMAND'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'AP_OD_SHUTTLE'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'declare',
            'p_region apex_plugin.t_region;',
            'p_plugin apex_plugin.t_plugin;',
            'l_result apex_plugin.t_region_ajax_result;',
            'begin',
            '',
            'p_region.attribute_03 := apex_application.g_x07; -- Filter',
            'p_region.attribute_02 := apex_application.g_x08; -- NumRows',
            'p_region.attribute_01 := apex_application.g_x09; -- Collection name',
            '',
            'l_result := collection_shuttle_region.ajax(p_region => p_region, p_plugin => p_plugin);',
            '',
            'end;'))
        );
    wwv_flow_api.create_flow_process(
            p_id=>wwv_flow_api.id(4978824659003628)
        ,p_process_sequence=>1
        ,p_process_point=>'ON_DEMAND'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'AP_OD_LOGERROR'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '  app_utils_pkg.log_apex_error;',
            'END;'))
        );
    wwv_flow_api.create_flow_process(
            p_id=>wwv_flow_api.id(12183128849413234)
        ,p_process_sequence=>1
        ,p_process_point=>'ON_DEMAND'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'AP_OD_PNC_COUNT'
        ,p_process_sql_clob=>'mod_off030_pkg.check_for_duplicate_pnc;'
        );
    wwv_flow_api.create_flow_process(
            p_id=>wwv_flow_api.id(9001168104113955)
        ,p_process_sequence=>1
        ,p_process_point=>'ON_NEW_INSTANCE'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'AP_ONS_SYS'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  app_standard_events_pkg.ap_ons;',
            'end;'))
        ,p_process_error_message=>' '
        );
    wwv_flow_api.create_flow_process(
            p_id=>wwv_flow_api.id(9001362609140760)
        ,p_process_sequence=>1
        ,p_process_point=>'ON_SUBMIT_BEFORE_COMPUTATION'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'AP_OSB_SYS'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  app_standard_events_pkg.ap_osb ;',
            'end;'))
        ,p_process_error_message=>' '
        );
end;
/
prompt --application/shared_components/logic/application_items
begin
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(2266225598816318)
        ,p_name=>'AI_APP_VERSION_NUMBER'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(2265809975816318)
        ,p_name=>'AI_AT_THEME_CSS_URL'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(2266018740816318)
        ,p_name=>'AI_AT_THEME_MODERN_CSS_URL'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(7873311722016240)
        ,p_name=>'AI_JUMPTO_NAV_URL'
        ,p_protection_level=>'I'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(5160620524427081)
        ,p_name=>'AI_NAV_SYS'
        ,p_protection_level=>'I'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(9682222736753450)
        ,p_name=>'AI_PREVIOUS_MODULE'
        ,p_protection_level=>'N'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(13377656577202987)
        ,p_name=>'AI_REQUEST_SYS'
        ,p_protection_level=>'I'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(12785822557102807)
        ,p_name=>'AI_THEME_CSS_URL_SYS'
        ,p_protection_level=>'I'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(13349132995927374)
        ,p_name=>'AI_THEME_IMAGES_URL_SYS'
        ,p_protection_level=>'I'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(13352338180553330)
        ,p_name=>'AI_THEME_JAVASCRIPT_URL_SYS'
        ,p_protection_level=>'N'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(13348753296923708)
        ,p_name=>'AI_THEME_URL_SYS'
        ,p_protection_level=>'I'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(11974205231051296)
        ,p_name=>'AI_TIMEOUT_SYS'
        ,p_protection_level=>'I'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(13364634296451695)
        ,p_name=>'AI_UI_APP_TITLE_SYS'
        ,p_protection_level=>'I'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(13364440745444039)
        ,p_name=>'AI_UI_BROWSER_TITLE_INFO_SYS'
        ,p_protection_level=>'N'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(13363936262244057)
        ,p_name=>'AI_UI_CONTEXT_INFO_SYS'
        ,p_protection_level=>'I'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(13364145266246673)
        ,p_name=>'AI_UI_FOOTER_INFO_SYS'
        ,p_protection_level=>'I'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(13363136514187379)
        ,p_name=>'AI_UI_LOGGED_IN_INFO_SYS'
        ,p_protection_level=>'I'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(13363353483192330)
        ,p_name=>'AI_UI_PAGE_TITLE_SYS'
        ,p_protection_level=>'I'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(2265902202816318)
        ,p_name=>'AI_UI_THEME_CSS_URL'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(2266105695816318)
        ,p_name=>'AI_UI_THEME_MODERN_CSS_URL'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(9004489340328265)
        ,p_name=>'FSP_AFTER_LOGIN_URL'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(1776013011624942)
        ,p_name=>'FSP_PROCESS_STATE_1768411579852643'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(8561707186517981)
        ,p_name=>'FSP_PROCESS_STATE_1881614066565837'
        );
    wwv_flow_api.create_flow_item(
            p_id=>wwv_flow_api.id(8565901136643148)
        ,p_name=>'FSP_PROCESS_STATE_8542508755295245'
        );
end;
/
prompt --application/shared_components/logic/application_computations
begin
    null;
end;
/
prompt --application/shared_components/navigation/tabs/standard
begin
    null;
end;
/
prompt --application/shared_components/navigation/tabs/parent
begin
    null;
end;
/
prompt --application/shared_components/user_interface/lovs
begin
    null;
end;
/
prompt --application/shared_components/navigation/trees
begin
    null;
end;
/
prompt --application/pages/page_groups
begin
    wwv_flow_api.create_page_group(
            p_id=>wwv_flow_api.id(6767457294623073)
        ,p_group_name=>'OFF030'
        ,p_group_desc=>'Component Examples'
        );
end;
/
prompt --application/comments
begin
    null;
end;
/
prompt --application/shared_components/navigation/breadcrumbs/breadcrumb
begin
    wwv_flow_api.create_menu(
            p_id=>wwv_flow_api.id(15976431735201091)
        ,p_name=>' Breadcrumb'
        );
end;
/
prompt --application/shared_components/user_interface/templates/page
begin
    wwv_flow_api.create_template(
            p_id=>wwv_flow_api.id(1621203972090849)
        ,p_theme_id=>101
        ,p_name=>'Error'
        ,p_is_popup=>false
        ,p_header_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!DOCTYPE HTML>',
            '<meta http-equiv="x-ua-compatible" content="IE=edge" />',
            '',
            '<!--[if lt IE 7 ]> <html lang="&BROWSER_LANGUAGE." class="ie6"> <![endif]-->',
            '<!--[if IE 7 ]>    <html lang="&BROWSER_LANGUAGE." class="ie7"> <![endif]-->',
            '<!--[if IE 8 ]>    <html lang="&BROWSER_LANGUAGE." class="ie8"> <![endif]-->',
            '<!--[if IE 9 ]>    <html lang="&BROWSER_LANGUAGE." class="ie9"> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <html lang="&BROWSER_LANGUAGE." class=""> ',
            '<!--<![endif]-->',
            '',
            '<head>',
            '',
            '<title>OASys&nbsp;&nbsp;&nbsp;&nbsp;Restricted&nbsp;&nbsp;&nbsp;&nbsp;&AI_UI_BROWSER_TITLE_INFO_SYS.</title>',
            '',
            '<!-- APEX_CSS START -->',
            '#APEX_CSS#',
            '<!-- APEX_CSS END -->',
            '',
            '<!-- APEX_JAVASCRIPT START -->',
            '#APEX_JAVASCRIPT#',
            '<!-- APEX_JAVASCRIPT END -->',
            '',
            '<!-- HEAD START -->',
            '#HEAD#',
            '<!-- HEAD END -->',
            '',
            '<!-- THEME_CSS START -->',
            '#THEME_STYLE_CSS#',
            '<!-- THEME_CSS END -->',
            '',
            '<!-- TEMPLATE_CSS START -->',
            '#THEME_CSS#',
            '#TEMPLATE_CSS#',
            '<!-- TEMPLATE_CSS END -->',
            '',
            '<!-- JQUERY UI CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- JQUERY UI CSS END -->',
            '',
            '<!-- OASYS CSS START -->',
            '<!--[if lt IE 7 ]> <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> ',
            '<!--<![endif]-->',
            '<!--  OASYS CSS END  -->',
            '',
            '<!-- AT CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- AT CSS END -->',
            '',
            '<!-- PAGE_CSS START -->',
            '#APPLICATION_CSS#',
            '#PAGE_CSS#',
            '<!-- PAGE_CSS END -->',
            '',
            '<!-- OASYS JQUERY START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/jquery/oasys-complete-jquery-1.9.1.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JQUERY END -->',
            '',
            '<!-- OASYS JS CUSTOM START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-core-combined-min.js&AI_APP_VERSION_NUMBER."></script>',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-submit.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JS CUSTOM END -->',
            '',
            '<!-- TEMPLATE_JAVASCRIPT START -->',
            '#THEME_JAVASCRIPT#',
            '#TEMPLATE_JAVASCRIPT#',
            '<!-- TEMPLATE_JAVASCRIPT END -->',
            '',
            '<!-- APPLICATION_JAVASCRIPT START -->',
            '#APPLICATION_JAVASCRIPT#',
            '<!-- APPLICATION_JAVASCRIPT END -->',
            '',
            '<!-- PAGE_JAVASCRIPT START -->',
            '#PAGE_JAVASCRIPT#',
            '<!-- PAGE_JAVASCRIPT END -->',
            '',
            '</head>',
            '',
            '<body class="home invisible" #ONLOAD#>',
            '',
            '#FORM_OPEN#',
            '',
            '<!-- HEAD END -->'))
        ,p_box=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- BODY START -->    ',
            '#REGION_POSITION_01#',
            '	<div id="container">',
            '		<div id="bannercontainer">',
            '			<div id="bannermodal">',
            '				<div id="bannerbarrnd">',
            '					<span id="bannerbarleftrnd">',
            '					<h1>&AI_UI_APP_TITLE_SYS.</h1>',
            '					</span>',
            '					<span id="bannerbarrightrnd">&AI_UI_LOGGED_IN_INFO_SYS.',
            '			<span class="buttonspan"><input id="logout" type="button" value="Logout" class="btn" ',
            '                               onclick="javascript:redirect(''f?p=EORSEC040:SEC040_LANDING:&SESSION.::::P10_SESSION_EXPIRED:N'')"/></span>',
            '					</span>',
            '				</div>',
            '			</div>',
            '			',
            '			<div id="bannertoolbar">',
            '				#REGION_POSITION_07#				',
            '			</div>',
            '		</div>',
            '		',
            '        <div class="messages">',
            '#SUCCESS_MESSAGE#',
            '#NOTIFICATION_MESSAGE#',
            '             <!-- -->',
            '        </div>',
            '#REGION_POSITION_02#		',
            '   <div class="filler"><!-- --></div> <!-- Needed for spacer under banner -->',
            '',
            '		<div id="contentwrapper">',
            '              <h1 class="hidden4jaws">Main Section</h1> ',
            '				   #BODY#',
            ''))
        ,p_footer_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- FOOTER START -->    ',
            '		</div>',
            '     </div>',
            '#REGION_POSITION_04#',
            '#FORM_CLOSE#',
            '#DEVELOPER_TOOLBAR#',
            '#GENERATED_CSS#',
            '#GENERATED_JAVASCRIPT#',
            '</body>',
            '</html>',
            '<!-- FOOTER END -->    ',
            ''))
        ,p_success_message=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<div id="messages">',
            '<a class="messageclose" class="pb" href="#" onClick="$x_Remove(''messages'')"><img src="/i/delete.gif" alt="Close Message" /><span style="display:none;">Close Message</span></a>',
            '<div class="info">',
            '  <div class="infoimage"><!-- --></div>',
            '  <div class="notification">',
            '  <h1 class="hidden4jaws">Information Message Notification Section</h1> ',
            '  <p>#SUCCESS_MESSAGE#</p>',
            '  </div>',
            '</div>',
            '</div>',
            ''))
        ,p_notification_message=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<div id="messages">',
            '<a class="messageclose" class="pb" href="#" onClick="$x_Remove(''messages'')"><img src="/i/delete.gif" alt="Close Message" /><span style="display:none;">Close Message</span></a>',
            '<div class="error">',
            '  <div class="errorimage"><!-- --></div>',
            '  <div class="notification">',
            '  <h1 class="hidden4jaws">Error Message Notification Section</h1> ',
            '  </div>',
            '</div>',
            '</div>'))
        ,p_region_table_cattributes=>' summary="" cellpadding="0" border="0" cellspacing="0" width="100%"'
        ,p_theme_class_id=>0
        ,p_error_page_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- Error message  -->',
            '<br><br><p id="oasysError"> </p>',
            '<div id="messages"><div class="error"><div class="errorimage"><!-- --></div>',
            '<div class="notification"><h1 class="hidden4jaws">Error Message Notification Section</h1><p>Error(s) have occurred<ul class="htmldbUlErr"><li>#MESSAGE#&nbsp;&nbsp;Please contact support quoting reference: <br><br>#ADDITIONAL_INFO#<br><br>Please Logou'
                ||'t of OASys.',
            '</li></ul></p>',
            '</div>',
            '</div>',
            '</div>',
            ''))
        ,p_grid_type=>'TABLE'
        ,p_grid_always_use_max_columns=>false
        ,p_grid_has_column_span=>true
        ,p_grid_emit_empty_leading_cols=>true
        ,p_grid_emit_empty_trail_cols=>false
        ,p_reference_id=>1319918855987509
        ,p_translate_this_template=>'N'
        );
    wwv_flow_api.create_template(
            p_id=>wwv_flow_api.id(5114141264252612)
        ,p_theme_id=>101
        ,p_name=>'Basic'
        ,p_is_popup=>false
        ,p_header_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!DOCTYPE HTML>',
            '<meta http-equiv="x-ua-compatible" content="IE=edge" />',
            '',
            '<!--[if lt IE 7 ]> <html lang="&BROWSER_LANGUAGE." class="ie6"> <![endif]-->',
            '<!--[if IE 7 ]>    <html lang="&BROWSER_LANGUAGE." class="ie7"> <![endif]-->',
            '<!--[if IE 8 ]>    <html lang="&BROWSER_LANGUAGE." class="ie8"> <![endif]-->',
            '<!--[if IE 9 ]>    <html lang="&BROWSER_LANGUAGE." class="ie9"> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <html lang="&BROWSER_LANGUAGE." class=""> ',
            '<!--<![endif]-->',
            '',
            '<head>',
            '',
            '<title>OASys&nbsp;&nbsp;&nbsp;&nbsp;Restricted&nbsp;&nbsp;&nbsp;&nbsp;&AI_UI_BROWSER_TITLE_INFO_SYS.</title>',
            '',
            '<!-- APEX_CSS START -->',
            '#APEX_CSS#',
            '<!-- APEX_CSS END -->',
            '',
            '<!-- APEX_JAVASCRIPT START -->',
            '#APEX_JAVASCRIPT#',
            '<!-- APEX_JAVASCRIPT END -->',
            '',
            '<!-- HEAD START -->',
            '#HEAD#',
            '<!-- HEAD END -->',
            '',
            '<!-- THEME_CSS START -->',
            '#THEME_STYLE_CSS#',
            '<!-- THEME_CSS END -->',
            '',
            '<!-- TEMPLATE_CSS START -->',
            '#THEME_CSS#',
            '#TEMPLATE_CSS#',
            '<!-- TEMPLATE_CSS END -->',
            '',
            '<!-- JQUERY UI CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- JQUERY UI CSS END -->',
            '',
            '<!-- OASYS CSS START -->',
            '<!--[if lt IE 7 ]> <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> ',
            '<!--<![endif]-->',
            '<!--  OASYS CSS END  -->',
            '',
            '<!-- AT CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- AT CSS END -->',
            '',
            '<!-- PAGE_CSS START -->',
            '#APPLICATION_CSS#',
            '#PAGE_CSS#',
            '<!-- PAGE_CSS END -->',
            '',
            '<!-- OASYS JQUERY START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/jquery/oasys-complete-jquery-1.9.1.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JQUERY END -->',
            '',
            '<!-- OASYS JS CUSTOM START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-core-combined-min.js&AI_APP_VERSION_NUMBER."></script>',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-submit.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JS CUSTOM END -->',
            '',
            '<!-- TEMPLATE_JAVASCRIPT START -->',
            '#THEME_JAVASCRIPT#',
            '#TEMPLATE_JAVASCRIPT#',
            '<!-- TEMPLATE_JAVASCRIPT END -->',
            '',
            '<!-- APPLICATION_JAVASCRIPT START -->',
            '#APPLICATION_JAVASCRIPT#',
            '<!-- APPLICATION_JAVASCRIPT END -->',
            '',
            '<!-- PAGE_JAVASCRIPT START -->',
            '#PAGE_JAVASCRIPT#',
            '<!-- PAGE_JAVASCRIPT END -->',
            '',
            '</head>',
            '',
            '<body class="home invisible" #ONLOAD#>',
            '',
            '#FORM_OPEN#',
            '',
            '<!-- HEAD END -->'))
        ,p_box=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '#REGION_POSITION_01#',
            '<div id="container">',
            '',
            '<div class="messages">',
            '#SUCCESS_MESSAGE#',
            '#NOTIFICATION_MESSAGE#',
            '</div>',
            '',
            '#REGION_POSITION_02#		',
            '',
            '			<div id="context">',
            '				<div id="contextleft">',
            '				&AI_UI_PAGE_TITLE_SYS.',
            '				</div>',
            '                                <div id="contextright">',
            '				&AI_UI_CONTEXT_INFO_SYS.',
            '				</div>',
            '                                <div class="topcurve"></div>			',
            '                        </div>',
            '',
            '<div id="CenterFull">',
            '',
            '  <h1 class="hidden4jaws">Main Section</h1> ',
            '',
            '  #BODY# ',
            '',
            '</div>'))
        ,p_footer_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- FOOTER START -->    ',
            '<div id="footer">',
            '   <p>&AI_UI_FOOTER_INFO_SYS.</p>',
            '   <div class="bottomcurve"></div>			',
            '</div>',
            '',
            '<div class="clear"></div>',
            '</div>',
            '</div>',
            '',
            '#REGION_POSITION_04#',
            '#FORM_CLOSE# ',
            '#DEVELOPER_TOOLBAR#',
            '#GENERATED_CSS#',
            '#GENERATED_JAVASCRIPT#',
            '',
            '</body>',
            '',
            '</html>',
            '',
            '',
            '',
            '',
            ''))
        ,p_success_message=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<div id="messages">',
            '<a class="messageclose" class="pb" href="#" onClick="$x_Remove(''messages'')"><img src="/i/delete.gif" alt="Close Message" /><span style="display:none;">Close Message</span></a>',
            '<div class="info">',
            '  <div class="infoimage"><!-- --></div>',
            '  <div class="notification">',
            '  <h1 class="hidden4jaws">Information Message Notification Section</h1> ',
            '  <p>#SUCCESS_MESSAGE#</p>',
            '  </div>',
            '</div>',
            '</div>',
            ''))
        ,p_notification_message=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<div id="messages">',
            '<a class="messageclose" class="pb" href="#" onClick="$x_Remove(''messages'')"><img src="/i/delete.gif" alt="Close Message" /><span style="display:none;">Close Message</span></a>',
            '<div class="error">',
            '  <div class="errorimage"><!-- --></div>',
            '  <div class="notification">',
            '  <h1 class="hidden4jaws">Error Message Notification Section</h1> ',
            '  <p>#MESSAGE#</p>',
            '  </div>',
            '</div>',
            '</div>'))
        ,p_region_table_cattributes=>' summary="" cellpadding="0" border="0" cellspacing="0" width="100%"'
        ,p_theme_class_id=>3
        ,p_grid_type=>'TABLE'
        ,p_grid_always_use_max_columns=>false
        ,p_grid_has_column_span=>true
        ,p_grid_emit_empty_leading_cols=>true
        ,p_grid_emit_empty_trail_cols=>false
        ,p_reference_id=>2324322917100158
        ,p_translate_this_template=>'N'
        );
    wwv_flow_api.create_template(
            p_id=>wwv_flow_api.id(20789214505320560)
        ,p_theme_id=>101
        ,p_name=>'Login'
        ,p_is_popup=>false
        ,p_header_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!DOCTYPE HTML>',
            '<meta http-equiv="x-ua-compatible" content="IE=edge" />',
            '',
            '<!--[if lt IE 7 ]> <html lang="&BROWSER_LANGUAGE." class="ie6"> <![endif]-->',
            '<!--[if IE 7 ]>    <html lang="&BROWSER_LANGUAGE." class="ie7"> <![endif]-->',
            '<!--[if IE 8 ]>    <html lang="&BROWSER_LANGUAGE." class="ie8"> <![endif]-->',
            '<!--[if IE 9 ]>    <html lang="&BROWSER_LANGUAGE." class="ie9"> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <html lang="&BROWSER_LANGUAGE." class=""> ',
            '<!--<![endif]-->',
            '',
            '<head>',
            '',
            '<title>OASys&nbsp;&nbsp;&nbsp;&nbsp;Restricted&nbsp;&nbsp;&nbsp;&nbsp;&AI_UI_BROWSER_TITLE_INFO_SYS.</title>',
            '',
            '<!-- APEX_CSS START -->',
            '#APEX_CSS#',
            '<!-- APEX_CSS END -->',
            '',
            '<!-- APEX_JAVASCRIPT START -->',
            '#APEX_JAVASCRIPT#',
            '<!-- APEX_JAVASCRIPT END -->',
            '',
            '<!-- HEAD START -->',
            '#HEAD#',
            '<!-- HEAD END -->',
            '',
            '<!-- THEME_CSS START -->',
            '#THEME_STYLE_CSS#',
            '<!-- THEME_CSS END -->',
            '',
            '<!-- TEMPLATE_CSS START -->',
            '#THEME_CSS#',
            '#TEMPLATE_CSS#',
            '<!-- TEMPLATE_CSS END -->',
            '',
            '<!-- JQUERY UI CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- JQUERY UI CSS END -->',
            '',
            '<!-- OASYS CSS START -->',
            '<!--[if lt IE 7 ]> <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> ',
            '<!--<![endif]-->',
            '<!--  OASYS CSS END  -->',
            '',
            '<!-- AT CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- AT CSS END -->',
            '',
            '<!-- PAGE_CSS START -->',
            '#APPLICATION_CSS#',
            '#PAGE_CSS#',
            '<!-- PAGE_CSS END -->',
            '',
            '<!-- OASYS JQUERY START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/jquery/oasys-complete-jquery-1.9.1.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JQUERY END -->',
            '',
            '<!-- OASYS JS CUSTOM START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-login-combined-min.js&AI_APP_VERSION_NUMBER."></script>',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-minimal-submit.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JS CUSTOM END -->',
            '',
            '<!-- TEMPLATE_JAVASCRIPT START -->',
            '#THEME_JAVASCRIPT#',
            '#TEMPLATE_JAVASCRIPT#',
            '<!-- TEMPLATE_JAVASCRIPT END -->',
            '',
            '<!-- APPLICATION_JAVASCRIPT START -->',
            '#APPLICATION_JAVASCRIPT#',
            '<!-- APPLICATION_JAVASCRIPT END -->',
            '',
            '<!-- PAGE_JAVASCRIPT START -->',
            '#PAGE_JAVASCRIPT#',
            '<!-- PAGE_JAVASCRIPT END -->',
            '',
            '</head>',
            '',
            '<body class="home invisible" #ONLOAD#>',
            '',
            '#FORM_OPEN#',
            '',
            '<!-- HEAD END -->'))
        ,p_box=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '#REGION_POSITION_01#',
            '	<div id="container">',
            '		<div id="bannercontainer">',
            '			<div id="banner">',
            '				<div id="bannerbarrnd">',
            '					<span id="bannerbarleftrnd">',
            '                                                     <span id="mojlogo">',
            '					<img class="mojlogo" alt="MOJ Logo" src="&AI_THEME_IMAGES_URL_SYS.MOJTransSmall.png"/></span>',
            '                                                     </span>',
            '					<h3 id="bannerbarrightrnd">&AI_UI_APP_TITLE_SYS.',
            '					</h3>',
            '				</div>',
            '			</div>',
            '			<div id="bannertoolbar">',
            '			</div>',
            '		</div>',
            '',
            '',
            '<div class="messages"><!-- -->',
            '#SUCCESS_MESSAGE#',
            '#NOTIFICATION_MESSAGE#',
            '</div>',
            '',
            '<div id="contentwrapper">',
            '  <div id="logincontent">',
            '  <!-- Content -->',
            '  <div id="login">',
            '<h1 class="hidden4jaws">Main Section</h1> ',
            '    <div id="loginbodyheader">',
            '	<h2>Login</h2>',
            '        <div class="topcurve"></div>			',
            '    </div> 		  ',
            '    <div id="loginbody">',
            '        #BODY# ',
            '    </div>',
            '    <div id="loginbodyfooter">',
            '        <div class="bottomcurve"></div>',
            '    </div>',
            '  </div>',
            '</div>',
            '	',
            '',
            ''))
        ,p_footer_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- FOOTER START -->    ',
            '			<div id="loginfooter">',
            '                           <p><strong>Restricted</strong></p>   			',
            '			</div>',
            '',
            '		<!-- contentwrapper --></div>',
            '	</div>',
            '',
            '#REGION_POSITION_04#',
            '',
            '#FORM_CLOSE#',
            '',
            '#DEVELOPER_TOOLBAR#',
            '#GENERATED_CSS#',
            '#GENERATED_JAVASCRIPT#',
            '</body>',
            '</html>',
            '<!-- FOOTER END -->    '))
        ,p_success_message=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<div id="messages">',
            '<a class="messageclose" class="pb" href="#" onClick="$x_Remove(''messages'')"><img src="/i/delete.gif" alt="Close Message" /><span style="display:none;">Close Message</span></a>',
            '<div class="info">',
            '  <div class="infoimage"><!-- --></div>',
            '  <div class="notification">',
            '  <h1 class="hidden4jaws">Information Message Notification Section</h1> ',
            '  <p>#SUCCESS_MESSAGE#</p>',
            '  </div>',
            '</div>',
            '</div>',
            ''))
        ,p_notification_message=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<div id="messages">',
            '<a class="messageclose" class="pb" href="#" onClick="$x_Remove(''messages'')"><img src="/i/delete.gif" alt="Close Message" /><span style="display:none;">Close Message</span></a>',
            '<div class="error">',
            '  <div class="errorimage"><!-- --></div>',
            '  <div class="notification">',
            '  <h1 class="hidden4jaws">Error Message Notification Section</h1> ',
            '  <p>#MESSAGE#</p>',
            '  </div>',
            '</div>',
            '</div>'))
        ,p_region_table_cattributes=>' summary="" cellpadding="0" border="0" cellspacing="0" width="100%"'
        ,p_theme_class_id=>6
        ,p_grid_type=>'TABLE'
        ,p_grid_always_use_max_columns=>false
        ,p_grid_has_column_span=>true
        ,p_grid_emit_empty_leading_cols=>true
        ,p_grid_emit_empty_trail_cols=>false
        ,p_reference_id=>15748765164117944
        ,p_translate_this_template=>'N'
        );
    wwv_flow_api.create_template(
            p_id=>wwv_flow_api.id(20789318053320561)
        ,p_theme_id=>101
        ,p_name=>'Standard'
        ,p_is_popup=>false
        ,p_header_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!DOCTYPE HTML>',
            '<meta http-equiv="x-ua-compatible" content="IE=edge" />',
            '',
            '<!--[if lt IE 7 ]> <html lang="&BROWSER_LANGUAGE." class="ie6"> <![endif]-->',
            '<!--[if IE 7 ]>    <html lang="&BROWSER_LANGUAGE." class="ie7"> <![endif]-->',
            '<!--[if IE 8 ]>    <html lang="&BROWSER_LANGUAGE." class="ie8"> <![endif]-->',
            '<!--[if IE 9 ]>    <html lang="&BROWSER_LANGUAGE." class="ie9"> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <html lang="&BROWSER_LANGUAGE." class=""> ',
            '<!--<![endif]-->',
            '',
            '<head>',
            '',
            '<title>OASys&nbsp;&nbsp;&nbsp;&nbsp;Restricted&nbsp;&nbsp;&nbsp;&nbsp;&AI_UI_BROWSER_TITLE_INFO_SYS.</title>',
            '',
            '<!-- APEX_CSS START -->',
            '#APEX_CSS#',
            '<!-- APEX_CSS END -->',
            '',
            '<!-- APEX_JAVASCRIPT START -->',
            '#APEX_JAVASCRIPT#',
            '<!-- APEX_JAVASCRIPT END -->',
            '',
            '<!-- HEAD START -->',
            '#HEAD#',
            '<!-- HEAD END -->',
            '',
            '<!-- THEME_CSS START -->',
            '#THEME_STYLE_CSS#',
            '<!-- THEME_CSS END -->',
            '',
            '<!-- TEMPLATE_CSS START -->',
            '#THEME_CSS#',
            '#TEMPLATE_CSS#',
            '<!-- TEMPLATE_CSS END -->',
            '',
            '<!-- JQUERY UI CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- JQUERY UI CSS END -->',
            '',
            '<!-- OASYS CSS START -->',
            '<!--[if lt IE 7 ]> <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> ',
            '<!--<![endif]-->',
            '<!--  OASYS CSS END  -->',
            '',
            '',
            '<!-- AT CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- AT CSS END -->',
            '',
            '<!-- PAGE_CSS START -->',
            '#APPLICATION_CSS#',
            '#PAGE_CSS#',
            '<!-- PAGE_CSS END -->',
            '',
            '<!-- OASYS JQUERY START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/jquery/oasys-complete-jquery-1.9.1.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JQUERY END -->',
            '',
            '<!-- OASYS JS CUSTOM START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-core-combined-min.js&AI_APP_VERSION_NUMBER."></script>',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-submit.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JS CUSTOM END -->',
            '',
            '<!-- TEMPLATE_JAVASCRIPT START -->',
            '#THEME_JAVASCRIPT#',
            '#TEMPLATE_JAVASCRIPT#',
            '<!-- TEMPLATE_JAVASCRIPT END -->',
            '',
            '<!-- APPLICATION_JAVASCRIPT START -->',
            '#APPLICATION_JAVASCRIPT#',
            '<!-- APPLICATION_JAVASCRIPT END -->',
            '',
            '<!-- PAGE_JAVASCRIPT START -->',
            '#PAGE_JAVASCRIPT#',
            '<!-- PAGE_JAVASCRIPT END -->',
            '',
            '</head>',
            '',
            '<body class="home invisible" #ONLOAD#>',
            '',
            '#FORM_OPEN#',
            '',
            '<!-- HEAD END -->'))
        ,p_box=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- BODY START -->    ',
            '#REGION_POSITION_01#',
            '	<div id="container">',
            '		<div id="bannercontainer">',
            '			<div id="banner">',
            '				<div id="bannerbarrnd">',
            '					<h2 id="bannerbarleftrnd">',
            '					&AI_UI_APP_TITLE_SYS.',
            '					</h2>',
            '					<span id="bannerbarrightrnd">&AI_UI_LOGGED_IN_INFO_SYS.',
            '					  <span class="buttonspan"><input id="logout" type="button" value="Logout" class="btn" ',
            '                               onclick="javascript:redirect(''f?p=EORSEC040:SEC040_LANDING:&SESSION.::::P10_SESSION_EXPIRED:N'')"/></span>',
            '					</span>',
            '				</div>',
            '',
            '				#REGION_POSITION_08#				',
            '			</div>',
            '			',
            '			<div id="bannertoolbar">',
            '				#REGION_POSITION_07#				',
            '			</div>',
            '            #REGION_POSITION_06#',
            '		</div>',
            '		',
            '        <div class="messages">',
            '#SUCCESS_MESSAGE#',
            '#NOTIFICATION_MESSAGE#',
            '             <!-- -->',
            '        </div>',
            '#REGION_POSITION_02#		',
            '		<div id="contentwrapper">',
            '		',
            '			<div id="context">',
            '				<div id="contextleft">',
            '				&AI_UI_PAGE_TITLE_SYS.',
            '				</div>',
            '                                <div id="contextright">',
            '				&AI_UI_CONTEXT_INFO_SYS.',
            '				</div>',
            '                                <div class="topcurve"></div>			',
            '                        </div>',
            '',
            '			<div id="content">',
            '				<div id="CenterFull">',
            '                                     <h1 class="hidden4jaws">Main Section</h1> ',
            '				   #BODY#',
            '				</div><!-- CenterFull -->',
            '<!-- BODY START -->    '))
        ,p_footer_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- FOOTER START -->    ',
            '			<div id="footer">',
            '		           <p>&AI_UI_FOOTER_INFO_SYS.</p>',
            '  			   <div class="bottomcurve"></div>			',
            '			</div>',
            '		</div>',
            '		<!-- contentwrapper --></div>',
            '	</div>',
            '',
            '#REGION_POSITION_04#',
            '',
            '#FORM_CLOSE#',
            '',
            '#DEVELOPER_TOOLBAR#',
            '#GENERATED_CSS#',
            '#GENERATED_JAVASCRIPT#',
            '</body>',
            '</html>',
            '<!-- FOOTER END -->    ',
            ''))
        ,p_success_message=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<div id="messages">',
            '<a class="messageclose" class="pb" href="#" onClick="$x_Remove(''messages'')"><img src="/i/delete.gif" alt="Close Message" /><span style="display:none;">Close Message</span></a>',
            '<div class="info">',
            '  <div class="infoimage"><!-- --></div>',
            '  <div class="notification">',
            '  <h1 class="hidden4jaws">Information Message Notification Section</h1> ',
            '  <p>#SUCCESS_MESSAGE#</p>',
            '  </div>',
            '</div>',
            '</div>'))
        ,p_notification_message=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<div id="messages">',
            '<a class="messageclose" class="pb" href="#" onClick="$x_Remove(''messages'')"><img src="/i/delete.gif" alt="Close Message" /><span style="display:none;">Close Message</span></a>',
            '<div class="error">',
            '  <div class="errorimage"><!-- --></div>',
            '  <div class="notification">',
            '  <h1 class="hidden4jaws">Error Message Notification Section</h1> ',
            '  <p>#MESSAGE#</p>',
            '  </div>',
            '</div>',
            '</div>'))
        ,p_region_table_cattributes=>' summary="" cellpadding="0" border="0" cellspacing="0" width="100%"'
        ,p_theme_class_id=>3
        ,p_grid_type=>'TABLE'
        ,p_grid_always_use_max_columns=>false
        ,p_grid_has_column_span=>true
        ,p_grid_emit_empty_leading_cols=>true
        ,p_grid_emit_empty_trail_cols=>false
        ,p_reference_id=>15748868712117945
        ,p_translate_this_template=>'N'
        );
    wwv_flow_api.create_template(
            p_id=>wwv_flow_api.id(20789413245320561)
        ,p_theme_id=>101
        ,p_name=>'Modal'
        ,p_is_popup=>false
        ,p_header_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!DOCTYPE HTML>',
            '<meta http-equiv="x-ua-compatible" content="IE=edge" />',
            '',
            '<!--[if lt IE 7 ]> <html lang="&BROWSER_LANGUAGE." class="ie6"> <![endif]-->',
            '<!--[if IE 7 ]>    <html lang="&BROWSER_LANGUAGE." class="ie7"> <![endif]-->',
            '<!--[if IE 8 ]>    <html lang="&BROWSER_LANGUAGE." class="ie8"> <![endif]-->',
            '<!--[if IE 9 ]>    <html lang="&BROWSER_LANGUAGE." class="ie9"> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <html lang="&BROWSER_LANGUAGE." class=""> ',
            '<!--<![endif]-->',
            '',
            '<head>',
            '',
            '<title>OASys&nbsp;&nbsp;&nbsp;&nbsp;Restricted&nbsp;&nbsp;&nbsp;&nbsp;&AI_UI_BROWSER_TITLE_INFO_SYS.</title>',
            '',
            '<!-- APEX_CSS START -->',
            '#APEX_CSS#',
            '<!-- APEX_CSS END -->',
            '',
            '<!-- APEX_JAVASCRIPT START -->',
            '#APEX_JAVASCRIPT#',
            '<!-- APEX_JAVASCRIPT END -->',
            '',
            '<!-- HEAD START -->',
            '#HEAD#',
            '<!-- HEAD END -->',
            '',
            '<!-- THEME_CSS START -->',
            '#THEME_STYLE_CSS#',
            '<!-- THEME_CSS END -->',
            '',
            '<!-- TEMPLATE_CSS START -->',
            '#THEME_CSS#',
            '#TEMPLATE_CSS#',
            '<!-- TEMPLATE_CSS END -->',
            '<!-- JQUERY UI CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- JQUERY UI CSS END -->',
            '',
            '<!-- OASYS CSS START -->',
            '<!--[if lt IE 7 ]> <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> ',
            '<!--<![endif]-->',
            '<!--  OASYS CSS END  -->',
            '',
            '<!-- AT CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- AT CSS END -->',
            '',
            '<!-- PAGE_CSS START -->',
            '#APPLICATION_CSS#',
            '#PAGE_CSS#',
            '<!-- PAGE_CSS END -->',
            '',
            '<!-- OASYS JQUERY START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/jquery/oasys-complete-jquery-1.9.1.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JQUERY END -->',
            '',
            '<!-- OASYS JS CUSTOM START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-core-combined-min.js&AI_APP_VERSION_NUMBER."></script>',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-submit.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JS CUSTOM END -->',
            '',
            '<!-- TEMPLATE_JAVASCRIPT START -->',
            '#THEME_JAVASCRIPT#',
            '#TEMPLATE_JAVASCRIPT#',
            '<!-- TEMPLATE_JAVASCRIPT END -->',
            '',
            '<!-- APPLICATION_JAVASCRIPT START -->',
            '#APPLICATION_JAVASCRIPT#',
            '<!-- APPLICATION_JAVASCRIPT END -->',
            '',
            '<!--  OASYS JAVASCRIPT END -->',
            '',
            '<!-- PAGE_JAVASCRIPT START -->',
            '#PAGE_JAVASCRIPT#',
            '<!-- PAGE_JAVASCRIPT END -->',
            '',
            '</head>',
            '',
            '<body class="home invisible" #ONLOAD#>',
            '',
            '#FORM_OPEN#',
            '',
            '<!-- HEAD END -->'))
        ,p_box=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- BODY START -->    ',
            '#REGION_POSITION_01#',
            '	<div id="container">',
            '		<div id="bannercontainer">',
            '			<div id="bannermodal">',
            '				<div id="bannerbarrnd">',
            '					<h3 id="bannerbarleftrnd">',
            '					&AI_UI_APP_TITLE_SYS.',
            '					</h3>',
            '					<span id="bannerbarrightrnd">&AI_UI_LOGGED_IN_INFO_SYS.',
            '					  <span class="buttonspan"><input id="logout" type="button" value="Logout" class="btn" ',
            '                               onclick="javascript:redirect(''f?p=EORSEC040:SEC040_LANDING:&SESSION.::::P10_SESSION_EXPIRED:N'')"/></span>',
            '					</span>',
            '				</div>',
            '			</div>',
            '			',
            '			<div id="bannertoolbar">',
            '				#REGION_POSITION_07#				',
            '			</div>',
            '		</div>',
            '		',
            '        <div class="messages">',
            '#SUCCESS_MESSAGE#',
            '#NOTIFICATION_MESSAGE#',
            '             <!-- -->',
            '        </div>',
            '   #REGION_POSITION_02#',
            '   <div class="filler"><!-- --></div> <!-- Needed for spacer under banner -->',
            '',
            '		<div id="contentwrapper">',
            '		',
            '			<div id="context">',
            '				<div id="contextleft">',
            '				&AI_UI_PAGE_TITLE_SYS.',
            '				</div> ',
            '                                <div id="contextright">',
            '				&AI_UI_CONTEXT_INFO_SYS.',
            '				</div>',
            '                                <div class="topcurve"></div>			',
            '                        </div>',
            '',
            '			<div id="content">',
            '				<div id="CenterFull">',
            '                                        <h1 class="hidden4jaws">Main Section</h1> ',
            '				   #BODY#',
            '				</div><!-- CenterFull -->',
            '<!-- BODY START -->    '))
        ,p_footer_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- FOOTER START -->',
            '                        <div class="clear"></div>',
            '                        <div class="access-help-message">',
            '                            <h3>To access Help, hold down the ALT key and press Q</h3>',
            '                        </div>',
            '			<div id="footer">',
            '		           <p>&AI_UI_FOOTER_INFO_SYS.</p>',
            '		        <div class="bottomcurve"></div>			',
            '			</div>',
            '		</div>',
            '		<!-- contentwrapper --></div>',
            '	</div>',
            '#REGION_POSITION_04#',
            '#FORM_CLOSE#',
            '#DEVELOPER_TOOLBAR#',
            '#GENERATED_CSS#',
            '#GENERATED_JAVASCRIPT#',
            '</body>',
            '</html>',
            '<!-- FOOTER END -->    ',
            ''))
        ,p_success_message=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<div id="messages">',
            '<a class="messageclose" class="pb" href="#" onClick="$x_Remove(''messages'')"><img src="/i/delete.gif" alt="Close Message" /><span style="display:none;">Close Message</span></a>',
            '<div class="info">',
            '  <div class="infoimage"><!-- --></div>',
            '  <div class="notification">',
            '  <h1 class="hidden4jaws">Information Message Notification Section</h1> ',
            '  <p>#SUCCESS_MESSAGE#</p>',
            '  </div>',
            '</div>',
            '</div>',
            ''))
        ,p_notification_message=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<div id="messages">',
            '<a class="messageclose" class="pb" href="#" onClick="$x_Remove(''messages'')"><img src="/i/delete.gif" alt="Close Message" /><span style="display:none;">Close Message</span></a>',
            '<div class="error">',
            '  <div class="errorimage"><!-- --></div>',
            '  <div class="notification">',
            '  <h1 class="hidden4jaws">Error Message Notification Section</h1> ',
            '  <p>#MESSAGE#</p>',
            '  </div>',
            '</div>',
            '</div>'))
        ,p_region_table_cattributes=>' summary="" cellpadding="0" border="0" cellspacing="0" width="100%"'
        ,p_theme_class_id=>3
        ,p_grid_type=>'TABLE'
        ,p_grid_always_use_max_columns=>false
        ,p_grid_has_column_span=>true
        ,p_grid_emit_empty_leading_cols=>true
        ,p_grid_emit_empty_trail_cols=>false
        ,p_reference_id=>15748963904117945
        ,p_translate_this_template=>'N'
        );
end;
/
begin
    wwv_flow_api.create_template(
            p_id=>wwv_flow_api.id(20789515246320561)
        ,p_theme_id=>101
        ,p_name=>'Search'
        ,p_is_popup=>false
        ,p_header_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!DOCTYPE HTML>',
            '<meta http-equiv="x-ua-compatible" content="IE=edge" />',
            '',
            '<!--[if lt IE 7 ]> <html lang="&BROWSER_LANGUAGE." class="ie6"> <![endif]-->',
            '<!--[if IE 7 ]>    <html lang="&BROWSER_LANGUAGE." class="ie7"> <![endif]-->',
            '<!--[if IE 8 ]>    <html lang="&BROWSER_LANGUAGE." class="ie8"> <![endif]-->',
            '<!--[if IE 9 ]>    <html lang="&BROWSER_LANGUAGE." class="ie9"> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <html lang="&BROWSER_LANGUAGE." class=""> ',
            '<!--<![endif]-->',
            '',
            '<head>',
            '',
            '<title>OASys&nbsp;&nbsp;&nbsp;&nbsp;Restricted&nbsp;&nbsp;&nbsp;&nbsp;&AI_UI_BROWSER_TITLE_INFO_SYS.</title>',
            '',
            '<!-- APEX_CSS START -->',
            '#APEX_CSS#',
            '<!-- APEX_CSS END -->',
            '',
            '<!-- APEX_JAVASCRIPT START -->',
            '#APEX_JAVASCRIPT#',
            '<!-- APEX_JAVASCRIPT END -->',
            '',
            '<!-- HEAD START -->',
            '#HEAD#',
            '<!-- HEAD END -->',
            '',
            '<!-- THEME_CSS START -->',
            '#THEME_STYLE_CSS#',
            '<!-- THEME_CSS END -->',
            '',
            '<!-- TEMPLATE_CSS START -->',
            '#THEME_CSS#',
            '#TEMPLATE_CSS#',
            '<!-- TEMPLATE_CSS END -->',
            '',
            '<!-- JQUERY UI CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- JQUERY UI CSS END -->',
            '',
            '<!-- OASYS CSS START -->',
            '<!--[if lt IE 7 ]> <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> ',
            '<!--<![endif]-->',
            '<!--  OASYS CSS END  -->',
            '',
            '<!-- AT CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- AT CSS END -->',
            '',
            '<!-- PAGE_CSS START -->',
            '#APPLICATION_CSS#',
            '#PAGE_CSS#',
            '<!-- PAGE_CSS END -->',
            '',
            '<!-- OASYS JQUERY START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/jquery/oasys-complete-jquery-1.9.1.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JQUERY END -->',
            '',
            '<!-- OASYS JS CUSTOM START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-core-combined-min.js&AI_APP_VERSION_NUMBER."></script>',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-submit.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JS CUSTOM END -->',
            '',
            '<!-- TEMPLATE_JAVASCRIPT START -->',
            '#THEME_JAVASCRIPT#',
            '#TEMPLATE_JAVASCRIPT#',
            '<!-- TEMPLATE_JAVASCRIPT END -->',
            '',
            '<!-- APPLICATION_JAVASCRIPT START -->',
            '#APPLICATION_JAVASCRIPT#',
            '<!-- APPLICATION_JAVASCRIPT END -->',
            '',
            '<!--  OASYS JAVASCRIPT END -->',
            '',
            '<!-- PAGE_JAVASCRIPT START -->',
            '#PAGE_JAVASCRIPT#',
            '<!-- PAGE_JAVASCRIPT END -->',
            '',
            '</head>',
            '',
            '<body class="home invisible" #ONLOAD#>',
            '#FORM_OPEN#',
            '<!-- HEAD END -->',
            ''))
        ,p_box=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- BODY START -->    ',
            '#REGION_POSITION_01#',
            '	<div id="container">',
            '		<div id="bannercontainer">',
            '			<div id="banner">',
            '				<div id="bannerbarrnd">',
            '					<h3 id="bannerbarleftrnd">&AI_UI_APP_TITLE_SYS.</h3>',
            '					<span id="bannerbarrightrnd">&AI_UI_LOGGED_IN_INFO_SYS.',
            '					  <span class="buttonspan"><input id="logout" type="button" value="Logout" class="btn" ',
            '                               onclick="javascript:redirect(''f?p=EORSEC040:SEC040_LANDING:&SESSION.::::P10_SESSION_EXPIRED:N'')"/></span>',
            '					</span>',
            '				</div>',
            '				#REGION_POSITION_08#				',
            '			</div>',
            '			',
            '			<div id="bannertoolbar">',
            '				#REGION_POSITION_07#				',
            '			</div>',
            '            #REGION_POSITION_06#',
            '		</div>',
            '',
            '',
            '#SUCCESS_MESSAGE#',
            '#NOTIFICATION_MESSAGE#',
            '#REGION_POSITION_02#		',
            '',
            '',
            '<div id="contentwrapper">',
            '	<div id="content">',
            '              <h1 class="hidden4jaws">Main Section</h1> ',
            '	     #REGION_POSITION_05#',
            '		<div id="CenterFull">',
            '                   <h1 class="hidden4jaws">Search Results Section</h1> ',
            '		#BODY#',
            '	</div> <!-- Content -->'))
        ,p_footer_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- footer -->		',
            '	<div id="footer">',
            '		<p>&AI_UI_FOOTER_INFO_SYS.</p>',
            '	        <div class="bottomcurve"></div>			',
            '           </div>',
            '     </div>',
            '     <!-- contentwrapper --></div>',
            '   </div>',
            '',
            '#REGION_POSITION_04#',
            '',
            '#FORM_CLOSE# ',
            '',
            '#DEVELOPER_TOOLBAR#',
            '#GENERATED_CSS#',
            '#GENERATED_JAVASCRIPT#',
            '</body>',
            '',
            '</html>',
            '		',
            ''))
        ,p_success_message=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<div id="messages">',
            '<a class="messageclose" class="pb" href="#" onClick="$x_Remove(''messages'')"><img src="/i/delete.gif" alt="Close Message" /><span style="display:none;">Close Message</span></a>',
            '<div class="info">',
            '  <div class="infoimage"><!-- --></div>',
            '  <div class="notification">',
            '  <h1 class="hidden4jaws">Information Message Notification Section</h1> ',
            '  <p>#SUCCESS_MESSAGE#</p>',
            '  </div>',
            '</div>',
            '</div>',
            ''))
        ,p_notification_message=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<div id="messages">',
            '<a class="messageclose" class="pb" href="#" onClick="$x_Remove(''messages'')"><img src="/i/delete.gif" alt="Close Message" /><span style="display:none;">Close Message</span></a>',
            '<div class="error">',
            '  <div class="errorimage"><!-- --></div>',
            '  <div class="notification">',
            '  <h1 class="hidden4jaws">Error Message Notification Section</h1> ',
            '  <p>#MESSAGE#</p>',
            '  </div>',
            '</div>',
            '</div>'))
        ,p_region_table_cattributes=>' summary="" cellpadding="0" border="0" cellspacing="0" width="100%"'
        ,p_theme_class_id=>3
        ,p_grid_type=>'TABLE'
        ,p_grid_always_use_max_columns=>false
        ,p_grid_has_column_span=>true
        ,p_grid_emit_empty_leading_cols=>true
        ,p_grid_emit_empty_trail_cols=>false
        ,p_reference_id=>15749065905117945
        ,p_translate_this_template=>'N'
        ,p_template_comment=>'12'
        );
    wwv_flow_api.create_template(
            p_id=>wwv_flow_api.id(20789621271320561)
        ,p_theme_id=>101
        ,p_name=>'LeftPanel'
        ,p_is_popup=>false
        ,p_javascript_code_onload=>'floatingMenu();'
        ,p_header_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!DOCTYPE HTML>',
            '<meta http-equiv="x-ua-compatible" content="IE=edge" />',
            '',
            '<!--[if lt IE 7 ]> <html lang="&BROWSER_LANGUAGE." class="ie6"> <![endif]-->',
            '<!--[if IE 7 ]>    <html lang="&BROWSER_LANGUAGE." class="ie7"> <![endif]-->',
            '<!--[if IE 8 ]>    <html lang="&BROWSER_LANGUAGE." class="ie8"> <![endif]-->',
            '<!--[if IE 9 ]>    <html lang="&BROWSER_LANGUAGE." class="ie9"> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <html lang="&BROWSER_LANGUAGE." class=""> ',
            '<!--<![endif]-->',
            '',
            '<head>',
            '',
            '<title>OASys&nbsp;&nbsp;&nbsp;&nbsp;Restricted&nbsp;&nbsp;&nbsp;&nbsp;&AI_UI_BROWSER_TITLE_INFO_SYS.</title>',
            '',
            '<!-- APEX_CSS START -->',
            '#APEX_CSS#',
            '<!-- APEX_CSS END -->',
            '',
            '<!-- APEX_JAVASCRIPT START -->',
            '#APEX_JAVASCRIPT#',
            '<!-- APEX_JAVASCRIPT END -->',
            '',
            '<!-- HEAD START -->',
            '#HEAD#',
            '<!-- HEAD END -->',
            '',
            '<!-- THEME_CSS START -->',
            '#THEME_STYLE_CSS#',
            '<!-- THEME_CSS END -->',
            '',
            '<!-- TEMPLATE_CSS START -->',
            '#THEME_CSS#',
            '#TEMPLATE_CSS#',
            '<!-- TEMPLATE_CSS END -->',
            '',
            '<!-- JQUERY UI CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- JQUERY UI CSS END -->',
            '',
            '<!-- OASYS CSS START -->',
            '<!--[if lt IE 7 ]> <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> ',
            '<!--<![endif]-->',
            '<!--  OASYS CSS END  -->',
            '',
            '<!-- AT CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- AT CSS END -->',
            '',
            '<!-- PAGE_CSS START -->',
            '#APPLICATION_CSS#',
            '#PAGE_CSS#',
            '<!-- PAGE_CSS END -->',
            '',
            '<!-- OASYS JQUERY START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/jquery/oasys-complete-jquery-1.9.1.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JQUERY END -->',
            '',
            '<!-- OASYS JS CUSTOM START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-core-combined-min.js&AI_APP_VERSION_NUMBER."></script>',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-submit.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JS CUSTOM END -->',
            '',
            '<!-- TEMPLATE_JAVASCRIPT START -->',
            '#THEME_JAVASCRIPT#',
            '#TEMPLATE_JAVASCRIPT#',
            '<!-- TEMPLATE_JAVASCRIPT END -->',
            '',
            '<!-- APPLICATION_JAVASCRIPT START -->',
            '#APPLICATION_JAVASCRIPT#',
            '<!-- APPLICATION_JAVASCRIPT END -->',
            '',
            '<!-- PAGE_JAVASCRIPT START -->',
            '#PAGE_JAVASCRIPT#',
            '<!-- PAGE_JAVASCRIPT END -->',
            '',
            '</head>',
            '',
            '<body class="home invisible" #ONLOAD# >',
            '#FORM_OPEN#',
            '<!-- HEAD END -->',
            ''))
        ,p_box=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- BODY START -->    ',
            '#REGION_POSITION_01#',
            '	<div id="container">',
            '		<div id="bannercontainer">',
            '			<div id="banner">',
            '				<div id="bannerbarrnd">',
            '					<h2 id="bannerbarleftrnd">',
            '					&AI_UI_APP_TITLE_SYS.',
            '					</h2>					<span id="bannerbarrightrnd">&AI_UI_LOGGED_IN_INFO_SYS.',
            '					  <span class="buttonspan"><input id="logout" type="button" value="Logout" class="btn" ',
            '                               onclick="javascript:redirect(''f?p=EORSEC040:SEC040_LANDING:&SESSION.::::P10_SESSION_EXPIRED:N'')"/></span>',
            '					</span>',
            '				</div>',
            '				#REGION_POSITION_08#				',
            '			</div> <!-- banner -->',
            '			<div id="bannertoolbar">',
            '				#REGION_POSITION_07#				',
            '			</div>',
            '            #REGION_POSITION_06#',
            '		</div> <!-- bannercontainer -->',
            '		',
            '        <div class="messages">',
            '#SUCCESS_MESSAGE#',
            '#NOTIFICATION_MESSAGE#',
            '             <!-- fix -->',
            '        </div>',
            '#REGION_POSITION_02#		',
            '		',
            '		<div id="contentwrapper">',
            '		',
            '			<div id="context">',
            '				<div id="contextleft">',
            '                                    &AI_UI_PAGE_TITLE_SYS.',
            '				</div>',
            '                                <div id="contextright">',
            '		   		    &AI_UI_CONTEXT_INFO_SYS.',
            '				</div>',
            '                                <div class="topcurve"></div>			',
            '			</div> <!-- context -->',
            '				',
            '			<div id="content">',
            '				<div id="leftmenu">',
            '              <h1 class="hidden4jaws">Left Navigation Menu Section</h1> ',
            '				  #REGION_POSITION_05#',
            '				</div>',
            '				<div id="CenterRight">',
            '              <h1 class="hidden4jaws">Main Section</h1> ',
            '				    #BODY#',
            '                                </div>',
            '                        </div> <!-- content -->'))
        ,p_footer_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- FOOTER START -->    ',
            '			<div id="footer">',
            '		           <p>&AI_UI_FOOTER_INFO_SYS.</p>',
            '  			   <div class="bottomcurve"></div>			',
            '			</div> <!-- footer-->',
            '		<!-- contentwrapper --></div>',
            '	</div> <!-- container -->',
            '',
            '#REGION_POSITION_04#',
            '',
            '#FORM_CLOSE#',
            '',
            '#DEVELOPER_TOOLBAR#',
            '#GENERATED_CSS#',
            '#GENERATED_JAVASCRIPT#',
            '</body>',
            '</html>',
            '<!-- FOOTER END -->    ',
            ''))
        ,p_success_message=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<div id="messages">',
            '<a class="messageclose" class="pb" href="#" onClick="$x_Remove(''messages'')"><img src="/i/delete.gif" alt="Close Message" /><span style="display:none;">Close Message</span></a>',
            '<div class="info">',
            '  <div class="infoimage"><!-- --></div>',
            '  <div class="notification">',
            '  <h1 class="hidden4jaws">Information Message Notification Section</h1> ',
            '  <p>#SUCCESS_MESSAGE#</p>',
            '  </div>',
            '</div>',
            '</div>',
            ''))
        ,p_notification_message=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<div id="messages">',
            '<a class="messageclose" class="pb" href="#" onClick="$x_Remove(''messages'')"><img src="/i/delete.gif" alt="Close Message" /><span style="display:none;">Close Message</span></a>',
            '<div class="error">',
            '  <div class="errorimage"><!-- --></div>',
            '  <div class="notification">',
            '  <h1 class="hidden4jaws">Error Message Notification Section</h1> ',
            '  <p>#MESSAGE#</p>',
            '  </div>',
            '</div>',
            '</div>'))
        ,p_region_table_cattributes=>' summary="" cellpadding="0" border="0" cellspacing="0" width="100%"'
        ,p_theme_class_id=>16
        ,p_grid_type=>'TABLE'
        ,p_grid_always_use_max_columns=>false
        ,p_grid_has_column_span=>true
        ,p_grid_emit_empty_leading_cols=>true
        ,p_grid_emit_empty_trail_cols=>false
        ,p_reference_id=>15749171930117945
        ,p_translate_this_template=>'N'
        );
end;
/
prompt --application/shared_components/user_interface/templates/button
begin
    wwv_flow_api.create_button_templates(
            p_id=>wwv_flow_api.id(5671908814747758)
        ,p_template_name=>'StandardNoOnclick'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<span class="buttonspan"><input id="#BUTTON_ID#"',
            '       type="button" ',
            '       value="#LABEL#" ',
            '       class="btn"',
            '       #BUTTON_ATTRIBUTES#',
            '/></span>'))
        ,p_reference_id=>1121523786780770
        ,p_translate_this_template=>'N'
        ,p_theme_class_id=>1
        ,p_template_comment=>'Standard Button no OnClick'
        ,p_theme_id=>101
        );
    wwv_flow_api.create_button_templates(
            p_id=>wwv_flow_api.id(18172596926955673)
        ,p_template_name=>'Reset'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<span class="buttonspan"><input id="#BUTTON_ID#"',
            '       onclick="javascript:appdosubmit(''RESET'')"',
            '       type="button" ',
            '       value="Reset" ',
            '       class="btn"',
            '/></span>'))
        ,p_reference_id=>13132147585753057
        ,p_translate_this_template=>'N'
        ,p_theme_class_id=>1
        ,p_template_comment=>'Reset'
        ,p_theme_id=>101
        );
    wwv_flow_api.create_button_templates(
            p_id=>wwv_flow_api.id(18172669699957248)
        ,p_template_name=>'Save as Default View'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<span class="buttonspan">',
            '<input id="#BUTTON_ID#"',
            '       onclick="javascript:appdosubmit(''SAVEASDEFAULTVIEW'')"',
            '       type="button" ',
            '       value="SaveAsDefaultView" ',
            '       class="btn"',
            '/>',
            '</span>'))
        ,p_reference_id=>13132220358754632
        ,p_translate_this_template=>'N'
        ,p_theme_class_id=>1
        ,p_template_comment=>'Save As Default View Button'
        ,p_theme_id=>101
        );
    wwv_flow_api.create_button_templates(
            p_id=>wwv_flow_api.id(18172780088960231)
        ,p_template_name=>'Search'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<span class="buttonspan"><input id="#BUTTON_ID#"',
            '       onclick="javascript:appdosubmit(''SEARCH'')"',
            '       type="button" ',
            '       value="Search" ',
            '       class="btn"',
            '/></span>'))
        ,p_reference_id=>13132330747757615
        ,p_translate_this_template=>'N'
        ,p_theme_class_id=>1
        ,p_template_comment=>'Search Button'
        ,p_theme_id=>101
        );
    wwv_flow_api.create_button_templates(
            p_id=>wwv_flow_api.id(20790115155320563)
        ,p_template_name=>'Standard'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<span class="buttonspan"><input id="#BUTTON_ID#"',
            '       onclick="#LINK#"',
            '       type="button" ',
            '       value="#LABEL#" ',
            '       class="btn"',
            '       #BUTTON_ATTRIBUTES#',
            '/></span>'))
        ,p_reference_id=>15749665814117947
        ,p_translate_this_template=>'N'
        ,p_theme_class_id=>1
        ,p_template_comment=>'Standard Button'
        ,p_theme_id=>101
        );
    wwv_flow_api.create_button_templates(
            p_id=>wwv_flow_api.id(20790204340320563)
        ,p_template_name=>'Save'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<span class="buttonspan"><input id="#BUTTON_ID#"',
            '       onclick="javascript:appdosubmit(''SAVE'')"',
            '       type="button" ',
            '       value="Save" ',
            '       class="btn"',
            '/></span>'))
        ,p_reference_id=>15749754999117947
        ,p_translate_this_template=>'N'
        ,p_theme_class_id=>1
        ,p_template_comment=>'Save'
        ,p_theme_id=>101
        );
    wwv_flow_api.create_button_templates(
            p_id=>wwv_flow_api.id(20790303721320563)
        ,p_template_name=>'Close'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<span class="buttonspan"><input id="#BUTTON_ID#"',
            '       onclick="javascript:appdosubmit(''CLOSE'')"',
            '       type="button" ',
            '       value="Close" ',
            '       class="btn"',
            '/></span>'))
        ,p_reference_id=>15749854380117947
        ,p_translate_this_template=>'N'
        ,p_theme_class_id=>1
        ,p_template_comment=>'Close'
        ,p_theme_id=>101
        );
end;
/
prompt --application/shared_components/user_interface/templates/region
begin
    wwv_flow_api.create_plug_template(
            p_id=>wwv_flow_api.id(3341723590971059)
        ,p_layout=>'TABLE'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- Form Region -->',
            '<div class="RegionWrapper">',
            '	<div id="#REGION_STATIC_ID#" #REGION_ATTRIBUTES# class="RegionStandard">',
            '        <h2>#TITLE#</h2>',
            '#BODY#',
            '	  <div id="#REGION_ID#regionbuttons" class="regionbuttons">',
            '             <div class="prop"><!-- fix space --></div>',
            '	    <span id="#REGION_ID#regionbuttonsleft" class="regionbuttonsleft">#PREVIOUS# <!-- fix space --></span>',
            '	    <span id="#REGION_ID#regionbuttonsright" class="regionbuttonsright">#NEXT# <!-- fix space --></span>',
            '             <div class="clearm"><!-- fix space --></div>',
            '          </div>',
            '        </div>',
            '</div>',
            '<!-- Form Region -->',
            ''))
        ,p_page_plug_template_name=>'FormNoButton'
        ,p_theme_id=>101
        ,p_theme_class_id=>8
        ,p_default_label_alignment=>'RIGHT'
        ,p_default_field_alignment=>'LEFT'
        ,p_reference_id=>15750247446117947
        ,p_translate_this_template=>'N'
        );
    wwv_flow_api.create_plug_template(
            p_id=>wwv_flow_api.id(6452830739005671)
        ,p_layout=>'TABLE'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!--Region Minimal -->',
            '<div class="RegionWrapper">',
            '	<div class="RegionMinimalWithBorder" id="#REGION_STATIC_ID#" #REGION_ATTRIBUTES#>',
            '<div class="regionbuttonstop">',
            '<!-- -->',
            '<h2><span class="regionbuttonslefttop">',
            '<!-- -->#TITLE#',
            '</span></h2>',
            '<span class="regionbuttonsrighttop">',
            '<!-- -->#CREATE#',
            '</span>',
            '<!-- IE Jump fix -->',
            '<br style="line-height:0; font-size:1px; height:1px; clear:all">',
            '</div>',
            '        #BODY# ',
            '	  <div class="regionbuttons">',
            '             <div class="prop"><!-- fix space --></div>',
            '			<span class="regionbuttonsleft">#PREVIOUS# <!-- fix space --></span>',
            '			<span class="regionbuttonsright">#NEXT# <!-- fix space --></span>',
            '             <div class="clearm"><!-- fix space --></div>',
            '          </div>',
            '        </div>',
            '<br style="clear:both">',
            '</div>',
            ''))
        ,p_page_plug_template_name=>'MinimalWithBorder'
        ,p_plug_table_bgcolor=>'#ffffff'
        ,p_theme_id=>101
        ,p_theme_class_id=>9
        ,p_plug_heading_bgcolor=>'#ffffff'
        ,p_plug_font_size=>'-1'
        ,p_default_label_alignment=>'RIGHT'
        ,p_default_field_alignment=>'LEFT'
        ,p_reference_id=>5495732475612677
        ,p_translate_this_template=>'N'
        );
    wwv_flow_api.create_plug_template(
            p_id=>wwv_flow_api.id(8357330651735949)
        ,p_layout=>'TABLE'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- Sub Region -->',
            '<div class="RegionWrapper">',
            '	<div id="#REGION_STATIC_ID#" #REGION_ATTRIBUTES# class="SubRegionStandard">',
            '        <h5>#TITLE#</h5>',
            '#BODY#',
            '	  <div id="#REGION_ID#regionbuttons">',
            '             <div class="prop"><!-- fix space --></div>',
            '			<span id="#REGION_ID#regionbuttonsleft">#PREVIOUS# <!-- fix space --></span>',
            '			<span id="#REGION_ID#regionbuttonsright">#NEXT# <!-- fix space --></span>',
            '             <div class="clearm"><!-- fix space --></div>',
            '          </div>',
            '        </div>',
            '</div>',
            '<!-- Sub Region -->		',
            ''))
        ,p_page_plug_template_name=>'SubRegion'
        ,p_theme_id=>101
        ,p_theme_class_id=>21
        ,p_default_label_alignment=>'RIGHT'
        ,p_default_field_alignment=>'LEFT'
        ,p_reference_id=>5698514614870195
        ,p_translate_this_template=>'N'
        );
    wwv_flow_api.create_plug_template(
            p_id=>wwv_flow_api.id(8357723720735951)
        ,p_layout=>'TABLE'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- Sub Region No Header-->',
            '<div class="RegionWrapper">',
            '	<div id="#REGION_STATIC_ID#" #REGION_ATTRIBUTES# class="SubRegionStandardNoHeader">',
            '        <h5>#TITLE#</h5>',
            '#BODY#',
            '	  <div id="#REGION_ID#regionbuttons">',
            '             <div class="prop"><!-- fix space --></div>',
            '		<span id="#REGION_ID#regionbuttonsleft">#PREVIOUS# <!-- fix space --></span>',
            '		<span id="#REGION_ID#regionbuttonsright">#NEXT# <!-- fix space --></span>',
            '             <div class="clearm"><!-- fix space --></div>',
            '          </div>',
            '        </div>',
            '</div>',
            '<!-- Sub Region No Header-->		',
            ''))
        ,p_page_plug_template_name=>'SubRegionMinimal'
        ,p_theme_id=>101
        ,p_theme_class_id=>21
        ,p_default_label_alignment=>'RIGHT'
        ,p_default_field_alignment=>'LEFT'
        ,p_reference_id=>5698720501871915
        ,p_translate_this_template=>'N'
        );
    wwv_flow_api.create_plug_template(
            p_id=>wwv_flow_api.id(15557072664074263)
        ,p_layout=>'TABLE'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '   ',
            '   ',
            '   <div class="bannerbuttons">',
            '      <span class="bannerbuttonsleft">',
            '      #PREVIOUS#',
            '      &nbsp; <!-- need space as v-align doesnt work otherwise -->',
            '      </span>',
            '      <span class="bannerbuttonsright">',
            '      #NEXT#',
            '      &nbsp; <!-- need space as v-align doesnt work otherwise -->',
            '      </span>',
            '   </div>',
            '',
            ''))
        ,p_page_plug_template_name=>'ButtonBar'
        ,p_plug_table_bgcolor=>'#ffffff'
        ,p_theme_id=>101
        ,p_theme_class_id=>0
        ,p_plug_heading_bgcolor=>'#ffffff'
        ,p_plug_font_size=>'-1'
        ,p_default_label_alignment=>'RIGHT'
        ,p_default_field_alignment=>'LEFT'
        ,p_reference_id=>10516623322871647
        ,p_translate_this_template=>'N'
        );
    wwv_flow_api.create_plug_template(
            p_id=>wwv_flow_api.id(20790622026320563)
        ,p_layout=>'TABLE'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!--Region Minimal -->',
            '<div class="RegionWrapper">',
            '	<div class="RegionMinimal" id="#REGION_STATIC_ID#" #REGION_ATTRIBUTES#>',
            '<div class="regionbuttonstop">',
            '<!-- -->',
            '<h2><span class="regionbuttonslefttop">',
            '<!-- -->#TITLE#',
            '</span></h2>',
            '<span class="regionbuttonsrighttop">',
            '<!-- -->#CREATE#',
            '</span>',
            '',
            '<!-- IE Jump fix -->',
            '<br style="line-height:0; font-size:1px; height:1px; clear:all">',
            '',
            '</div>',
            '        #BODY# ',
            '	  <div class="regionbuttons">',
            '             <div class="prop"><!-- fix space --></div>',
            '			<span class="regionbuttonsleft">#PREVIOUS# <!-- fix space --></span>',
            '			<span class="regionbuttonsright">#NEXT# <!-- fix space --></span>',
            '             <div class="clearm"><!-- fix space --></div>',
            '          </div>',
            '        </div>',
            '</div>',
            ''))
        ,p_page_plug_template_name=>'Minimal'
        ,p_plug_table_bgcolor=>'#ffffff'
        ,p_theme_id=>101
        ,p_theme_class_id=>9
        ,p_plug_heading_bgcolor=>'#ffffff'
        ,p_plug_font_size=>'-1'
        ,p_default_label_alignment=>'RIGHT'
        ,p_default_field_alignment=>'LEFT'
        ,p_reference_id=>15750172685117947
        ,p_translate_this_template=>'N'
        );
    wwv_flow_api.create_plug_template(
            p_id=>wwv_flow_api.id(20790696787320563)
        ,p_layout=>'TABLE'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- Form Region -->',
            '<div class="RegionWrapper">',
            '	<div id="#REGION_STATIC_ID#" #REGION_ATTRIBUTES# class="RegionStandard">',
            '        <h2>#TITLE#</h2>',
            '#BODY#',
            '	  <div id="#REGION_ID#regionbuttons" class="regionbuttons">',
            '             <div class="prop"><!-- fix space --></div>',
            '	    <span id="#REGION_ID#regionbuttonsleft" class="regionbuttonsleft">#PREVIOUS# <!-- fix space --></span>',
            '	    <span id="#REGION_ID#regionbuttonsright" class="regionbuttonsright">#NEXT# <!-- fix space --></span>',
            '             <div class="clearm"><!-- fix space --></div>',
            '          </div>',
            '        </div>',
            '</div>',
            '<!-- Form Region -->',
            ''))
        ,p_page_plug_template_name=>'Form'
        ,p_theme_id=>101
        ,p_theme_class_id=>8
        ,p_default_label_alignment=>'RIGHT'
        ,p_default_field_alignment=>'LEFT'
        ,p_reference_id=>15750247446117947
        ,p_translate_this_template=>'N'
        );
    wwv_flow_api.create_plug_template(
            p_id=>wwv_flow_api.id(20791419266320563)
        ,p_layout=>'TABLE'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- searchform region -->',
            '<div id="searchtop"  #REGION_ATTRIBUTES#>',
            '',
            '  <h2><span id="searchleft"><strong>',
            '  <a id="searchCE" href="javascript:searchFormToggleCE();"><img id="searchregionce" src="#IMAGE_PREFIX#themes/moj_blue_theme/images/treeExpanded.gif" alt="">#TITLE#</a>',
            '  </strong></span>',
            '  </h2>',
            '',
            '  <div id="searchtopcurve"></div>',
            '</div>',
            '',
            '<div id="search"  >',
            '<br>',
            '  <div id="searchform">',
            '',
            '  <div id="searchforminner">',
            '    #BODY#',
            '    <div id="searchbuttons">',
            '    <span id="searchbuttonsleft"> 	  ',
            '    #PREVIOUS#',
            '    </span>',
            '    <span id="searchbuttonsright"> 	  ',
            '    #NEXT#',
            '    </span>',
            '  </div>',
            '',
            '  </div>',
            '',
            '</div>',
            '</div>',
            '<div id="searchfooter">',
            '</div>',
            '<!-- searchform region -->'))
        ,p_page_plug_template_name=>'SearchForm'
        ,p_plug_table_bgcolor=>'#ffffff'
        ,p_theme_id=>101
        ,p_theme_class_id=>8
        ,p_plug_heading_bgcolor=>'#ffffff'
        ,p_plug_font_size=>'-1'
        ,p_default_label_alignment=>'RIGHT'
        ,p_default_field_alignment=>'LEFT'
        ,p_reference_id=>15750969925117947
        ,p_translate_this_template=>'N'
        ,p_template_comment=>'Search Region'
        );
    wwv_flow_api.create_plug_template(
            p_id=>wwv_flow_api.id(20791510255320565)
        ,p_layout=>'TABLE'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- Region MinimalWithTitle -->',
            '<div class="RegionWrapper">',
            '   <div class="RegionMinimalWithHeader" id="#REGION_STATIC_ID#" #REGION_ATTRIBUTES#>',
            '<div class="regionbuttonstop">',
            '<!-- -->',
            '<h2><span class="regionbuttonslefttop">',
            '<!-- -->#TITLE#',
            '</span></h2>',
            '<span class="regionbuttonsrighttop">',
            '<!-- -->#CREATE#',
            '</span>',
            '<!-- IE Jump fix -->',
            '<br style="line-height:0; font-size:1px; height:1px; clear:all">',
            '</div>',
            '   #BODY# ',
            '	  <div class="regionbuttons">',
            '             <div class="prop"><!-- fix space --></div>',
            '			<span class="regionbuttonsleft">#PREVIOUS# <!-- fix space --></span>',
            '			<span class="regionbuttonsright">#NEXT# <!-- fix space --></span>',
            '             <div class="clearm"><!-- fix space --></div>',
            '          </div>',
            '   </div>',
            '<br style="clear:both">',
            '</div>',
            '<!-- Region MinimalWithTitle -->'))
        ,p_page_plug_template_name=>'MinimalWithTitle'
        ,p_theme_id=>101
        ,p_theme_class_id=>9
        ,p_default_label_alignment=>'RIGHT'
        ,p_default_field_alignment=>'LEFT'
        ,p_reference_id=>15751060914117949
        ,p_translate_this_template=>'N'
        );
    wwv_flow_api.create_plug_template(
            p_id=>wwv_flow_api.id(20791993046320565)
        ,p_layout=>'TABLE'
        ,p_template=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<!-- Tab Region -->',
            '<div class="RegionWrapper">',
            '	<div id="#REGION_STATIC_ID#" #REGION_ATTRIBUTES# class="RegionTab">',
            '		<h2>#TITLE#</h2>',
            '		#BODY#',
            '	  <div id="#REGION_ID#regionbuttons">',
            '             <div class="prop"><!-- fix space --></div>',
            '			<span id="#REGION_ID#regionbuttonsleft">#PREVIOUS# <!-- fix space --></span>',
            '			<span id="#REGION_ID#regionbuttonsright">#NEXT# <!-- fix space --></span>',
            '             <div class="clearm"><!-- fix space --></div>',
            '          </div>',
            '	</div>',
            '</div>',
            '<!-- Tab Region -->',
            ''))
        ,p_page_plug_template_name=>'Tab'
        ,p_plug_table_bgcolor=>'#ffffff'
        ,p_theme_id=>101
        ,p_theme_class_id=>8
        ,p_plug_heading_bgcolor=>'#ffffff'
        ,p_plug_font_size=>'-1'
        ,p_default_label_alignment=>'RIGHT'
        ,p_default_field_alignment=>'LEFT'
        ,p_reference_id=>15751543705117949
        ,p_translate_this_template=>'N'
        );
end;
/
prompt --application/shared_components/user_interface/templates/list
begin
    wwv_flow_api.create_list_template(
            p_id=>wwv_flow_api.id(20792703593320565)
        ,p_list_template_current=>'<a href="#LINK#" class="t4Button" style="background-color:#CCCCCC;">#TEXT#</a>'
        ,p_list_template_noncurrent=>'<a href="#LINK#" class="t4Button">#TEXT#</a>'
        ,p_list_template_name=>'Button List'
        ,p_theme_id=>101
        ,p_theme_class_id=>6
        ,p_list_template_before_rows=>'<div class="t4ButtonList">'
        ,p_list_template_after_rows=>'</div>'
        ,p_reference_id=>15752254252117949
        );
end;
/
prompt --application/shared_components/user_interface/templates/report
begin
    wwv_flow_api.create_row_template(
            p_id=>wwv_flow_api.id(15545672787664696)
        ,p_row_template_name=>'RowHighlight'
        ,p_row_template1=>'<td  class="evenRow" style="text-align:#ALIGN#" headers="#COLUMN_HEADER_NAME#">#COLUMN_VALUE#</td>'
        ,p_row_template2=>'<td class="oddRow" style="text-align:#ALIGN#" headers="#COLUMN_HEADER_NAME#">#COLUMN_VALUE#</td>'
        ,p_row_template_before_rows=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<table class="reporthighlight" #REPORT_ATTRIBUTES# id="report_#REGION_STATIC_ID#">',
            '<caption>Report Region</caption>',
            '',
            ''))
        ,p_row_template_after_rows=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '</tbody>',
            '</table>',
            '',
            '<div style="width: 100%;">',
            '  <div style="float:right;">',
            '  <table id="paginationtable" summary="Pagination Navigation">',
            '  #PAGINATION#                                                                  ',
            '  </table>',
            '  </div>',
            '</div> ',
            '<div class="clear"></div>',
            ''))
        ,p_row_template_table_attr=>'OMIT'
        ,p_row_template_type=>'GENERIC_COLUMNS'
        ,p_before_column_heading=>'<thead>'
        ,p_column_heading_template=>'<th id="#COLUMN_HEADER_NAME#" #ALIGNMENT#>#COLUMN_HEADER#</th>'
        ,p_after_column_heading=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '</thead>',
            '<tbody>'))
        ,p_row_template_display_cond1=>'EVEN_ROW_NUMBERS'
        ,p_row_template_display_cond2=>'ODD_ROW_NUMBERS'
        ,p_row_template_display_cond3=>'0'
        ,p_row_template_display_cond4=>'EVEN_ROW_NUMBERS'
        ,p_next_page_template=>'<a href="#LINK#">#PAGINATION_NEXT# &gt;</a>'
        ,p_previous_page_template=>'<a href="#LINK#">&lt; #PAGINATION_PREVIOUS#</a>'
        ,p_next_set_template=>'<a href="#LINK#">#PAGINATION_NEXT# &gt;</a>'
        ,p_previous_set_template=>'<a href="#LINK#">&lt; #PAGINATION_PREVIOUS#</a>'
        ,p_row_style_checked=>'#CCCCCC'
        ,p_theme_id=>101
        ,p_theme_class_id=>0
        ,p_reference_id=>10505223446462080
        ,p_translate_this_template=>'N'
        );
    begin
        wwv_flow_api.create_row_template_patch(
                p_id=>wwv_flow_api.id(15545672787664696)
            ,p_row_template_before_first=>'<tr #HIGHLIGHT_ROW#>'
            ,p_row_template_after_last=>'</tr>'
            );
    exception when others then null;
    end;
    wwv_flow_api.create_row_template(
            p_id=>wwv_flow_api.id(20794602676320568)
        ,p_row_template_name=>'Standard'
        ,p_row_template1=>'<td class="evenRow" style="text-align:#ALIGN#" headers="#COLUMN_HEADER_NAME#">#COLUMN_VALUE#</td>'
        ,p_row_template2=>'<td class="oddRow" style="text-align:#ALIGN#" headers="#COLUMN_HEADER_NAME#">#COLUMN_VALUE#</td>'
        ,p_row_template_before_rows=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<table class="reportstandard" #REPORT_ATTRIBUTES# id="report_#REGION_STATIC_ID#">',
            '<caption>Report Region</caption>',
            '',
            ''))
        ,p_row_template_after_rows=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '</tbody>',
            '</table>',
            '',
            '<div style="width: 100%;">',
            '  <div style="float:right;">',
            '  <table id="paginationtable" summary="Pagination Navigation">',
            '  #PAGINATION#                                                                  ',
            '  </table>',
            '  </div>',
            '</div> ',
            '<div class="clear"></div>',
            ''))
        ,p_row_template_table_attr=>'OMIT'
        ,p_row_template_type=>'GENERIC_COLUMNS'
        ,p_before_column_heading=>'<thead>'
        ,p_column_heading_template=>'<th id="#COLUMN_HEADER_NAME#" #ALIGNMENT#>#COLUMN_HEADER#</th>'
        ,p_after_column_heading=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '</thead>',
            '<tbody>'))
        ,p_row_template_display_cond1=>'EVEN_ROW_NUMBERS'
        ,p_row_template_display_cond2=>'ODD_ROW_NUMBERS'
        ,p_row_template_display_cond3=>'0'
        ,p_row_template_display_cond4=>'EVEN_ROW_NUMBERS'
        ,p_next_page_template=>'<a href="#LINK#">#PAGINATION_NEXT# &gt;</a>'
        ,p_previous_page_template=>'<a href="#LINK#">&lt; #PAGINATION_PREVIOUS#</a>'
        ,p_next_set_template=>'<a href="#LINK#">#PAGINATION_NEXT# &gt;</a>'
        ,p_previous_set_template=>'<a href="#LINK#">&lt; #PAGINATION_PREVIOUS#</a>'
        ,p_row_style_checked=>'#CCCCCC'
        ,p_theme_id=>101
        ,p_theme_class_id=>0
        ,p_reference_id=>15754153335117952
        ,p_translate_this_template=>'N'
        );
    begin
        wwv_flow_api.create_row_template_patch(
                p_id=>wwv_flow_api.id(20794602676320568)
            ,p_row_template_before_first=>'<tr #HIGHLIGHT_ROW#>'
            ,p_row_template_after_last=>'</tr>'
            );
    exception when others then null;
    end;
end;
/
prompt --application/shared_components/user_interface/templates/label
begin
    wwv_flow_api.create_field_template(
            p_id=>wwv_flow_api.id(20795019789320568)
        ,p_template_name=>'No Label'
        ,p_template_body1=>'<label for="#CURRENT_ITEM_NAME#" id="#LABEL_ID#" class="hidden4jaws">'
        ,p_template_body2=>'</label>'
        ,p_theme_id=>101
        ,p_theme_class_id=>13
        ,p_reference_id=>15754570448117952
        ,p_translate_this_template=>'N'
        );
    wwv_flow_api.create_field_template(
            p_id=>wwv_flow_api.id(20795102685320569)
        ,p_template_name=>'Optional Label'
        ,p_template_body1=>'<label for="#CURRENT_ITEM_NAME#" id="#LABEL_ID#" class="optional">'
        ,p_template_body2=>'</label>'
        ,p_theme_id=>101
        ,p_theme_class_id=>3
        ,p_reference_id=>15754653344117953
        ,p_translate_this_template=>'N'
        );
    wwv_flow_api.create_field_template(
            p_id=>wwv_flow_api.id(20795309951320569)
        ,p_template_name=>'Required Label'
        ,p_template_body1=>'<label for="#CURRENT_ITEM_NAME#" id="#LABEL_ID#" class="required">'
        ,p_template_body2=>'<em>*</em></label>'
        ,p_theme_id=>101
        ,p_theme_class_id=>4
        ,p_reference_id=>15754860610117953
        ,p_translate_this_template=>'N'
        );
end;
/
prompt --application/shared_components/user_interface/templates/breadcrumb
begin
    null;
end;
/
prompt --application/shared_components/user_interface/templates/popuplov
begin
    wwv_flow_api.create_popup_lov_template(
            p_id=>wwv_flow_api.id(20796297404320571)
        ,p_popup_icon=>'#IMAGE_PREFIX#themes/moj_blue_theme/images/popuplov.png'
        ,p_popup_icon_attr=>'width="17" height="18" alt="Popup Lov" style="margin-top:2px;"'
        ,p_page_name=>'winlov'
        ,p_page_title=>'List Of Values'
        ,p_page_html_head=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<html lang="&BROWSER_LANGUAGE.">',
            '<head>',
            '<title>#TITLE#</title>',
            '#APEX_CSS#',
            '#THEME_CSS#',
            '#THEME_STYLE_CSS#',
            '#APEX_JAVASCRIPT#',
            '<!DOCTYPE HTML>',
            '',
            '<!--[if lt IE 7 ]> <html lang="&BROWSER_LANGUAGE." class="ie6"> <![endif]-->',
            '<!--[if IE 7 ]>    <html lang="&BROWSER_LANGUAGE." class="ie7"> <![endif]-->',
            '<!--[if IE 8 ]>    <html lang="&BROWSER_LANGUAGE." class="ie8"> <![endif]-->',
            '<!--[if IE 9 ]>    <html lang="&BROWSER_LANGUAGE." class="ie9"> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <html lang="&BROWSER_LANGUAGE." class=""> ',
            '<!--<![endif]-->',
            '',
            '<head>',
            '',
            '<title>Search Dialog</title>',
            '',
            '<!-- JQUERY UI CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_UI_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_UI_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- JQUERY UI CSS END -->',
            '',
            '<!-- OASYS CSS START -->',
            '<!--[if lt IE 7 ]> <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]>    <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link href="#IMAGE_PREFIX#themes/moj_blue_theme/css/modern-oasys-css-combined-min.css&AI_APP_VERSION_NUMBER." rel="stylesheet" type="text/css" /> ',
            '<!--<![endif]-->',
            '<!--  OASYS CSS END  -->',
            '',
            '<!-- AT CSS START -->',
            '<!--[if lt IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 7 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 8 ]><link rel="stylesheet" href="&AI_AT_THEME_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if IE 9 ]> <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" /> <![endif]-->',
            '<!--[if (gt IE 9)|!(IE)]><!--> ',
            '                   <link rel="stylesheet" href="&AI_AT_THEME_MODERN_CSS_URL.&AI_APP_VERSION_NUMBER." type="text/css" />',
            '<!--<![endif]-->',
            '<!-- AT CSS END -->',
            '',
            '<!-- OASYS JQUERY START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/jquery/oasys-complete-jquery-1.9.1.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JQUERY END -->',
            '',
            '<!-- OASYS JS CUSTOM START -->',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-core-combined-min.js&AI_APP_VERSION_NUMBER."></script>',
            '<script type="text/javascript" src="#IMAGE_PREFIX#themes/moj_blue_theme/javascript/oasys-submit.js&AI_APP_VERSION_NUMBER."></script>',
            '<!-- OASYS JS CUSTOM END -->',
            '',
            '<!-- THEME_CSS START -->',
            '',
            '<!-- THEME_CSS END -->',
            '',
            '</head>'))
        ,p_page_body_attr=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'id="popuplov" onload="first_field()" oncontextmenu="return false;"',
            ''))
        ,p_before_field_text=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<script>',
            '$(document).ready(function(){',
            '',
            '  var i = 0;',
            '   $(''<label for="SEARCH" style="margin-left:10px; padding-bottom: 3px; vertical-align: middle;" title="Enter Text">Enter Text</label>'').insertBefore(''#SEARCH'');     ',
            '',
            '   $(''br:first'').css({display:''none''});',
            '  $(''a'').each(function() {',
            '     i = i+1;  ',
            '     if (i%2 > 0)',
            '	 {',
            '	   $(this).attr("class","lovalt");',
            '     }',
            '   });	',
            '',
            '   $(''body'').css(''cursor'',''auto'');',
            '',
            '   $("#SEARCH").keypress(function(event) {',
            '     if (event.keyCode == 10 || event.keyCode == 13) {',
            '       event.preventDefault();',
            '       if ( $(''#blockuiactive'').val()=="N" ) {',
            '          performAction(''SEARCH'', 1);',
            '       }',
            '     }',
            '   });',
            '',
            '});',
            '',
            'function first_field(){$("#SEARCH").focus();};',
            '',
            '</script>',
            '<input type="hidden" value="N" id="blockuiactive" disabled="disabled"/>',
            '<div id="atclassdiv" class="atclass" style="display:none"></div>',
            '',
            '<div class="popupheader">'))
        ,p_filter_width=>'50'
        ,p_filter_max_width=>'100'
        ,p_find_button_text=>'Search'
        ,p_find_button_attr=>'class="btn" style=" margin-bottom:10px;"'
        ,p_close_button_text=>'Close'
        ,p_close_button_attr=>'class="btn" style=" margin-bottom:10px;"'
        ,p_next_button_text=>'Next'
        ,p_next_button_attr=>'class="btnbasic"'
        ,p_prev_button_text=>'Previous'
        ,p_prev_button_attr=>'class="btnbasic"'
        ,p_after_field_text=>'</div>'
        ,p_scrollbars=>'1'
        ,p_resizable=>'1'
        ,p_width=>'660'
        ,p_height=>'470'
        ,p_result_row_x_of_y=>'<div class="lovrows">Row(s) #FIRST_ROW# - #LAST_ROW#</div>'
        ,p_result_rows_per_pg=>15
        ,p_before_result_set=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<div class="popupbody">',
            ''))
        ,p_theme_id=>101
        ,p_theme_class_id=>1
        ,p_reference_id=>15755848063117955
        ,p_translate_this_template=>'N'
        ,p_after_result_set=>'</div>'
        );
end;
/
prompt --application/shared_components/user_interface/templates/calendar
begin
    wwv_flow_api.create_calendar_template(
            p_id=>wwv_flow_api.id(20795692846320569)
        ,p_cal_template_name=>'Calendar'
        ,p_day_of_week_format=>'<th class="t4DayOfWeek">#IDAY#</th>'
        ,p_month_title_format=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<table cellspacing="0" cellpadding="0" border="0" summary="" class="t4CalendarHolder"> ',
            ' <tr>',
            '   <td class="t4MonthTitle">#IMONTH# #YYYY#</td>',
            ' </tr>',
            ' <tr>',
            ' <td>'))
        ,p_month_open_format=>'<table border="0" cellpadding="0" cellspacing="0" summary="0" class="t4Calendar">'
        ,p_month_close_format=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '</table></td>',
            '</tr>',
            '</table>',
            ''))
        ,p_day_title_format=>'<div class="t4DayTitle">#DD#</div>'
        ,p_day_open_format=>'<td class="t4Day" valign="top">#TITLE_FORMAT##DATA#'
        ,p_day_close_format=>'</td>'
        ,p_today_open_format=>'<td valign="top" class="t4Today">#TITLE_FORMAT##DATA#'
        ,p_weekend_title_format=>'<div class="t4WeekendDayTitle">#DD#</div>'
        ,p_weekend_open_format=>'<td valign="top" class="t4WeekendDay">#TITLE_FORMAT##DATA#'
        ,p_weekend_close_format=>'</td>'
        ,p_nonday_title_format=>'<div class="t4NonDayTitle">#DD#</div>'
        ,p_nonday_open_format=>'<td class="t4NonDay" valign="top">'
        ,p_nonday_close_format=>'</td>'
        ,p_week_open_format=>'<tr>'
        ,p_week_close_format=>'</tr> '
        ,p_daily_title_format=>'<th width="14%" class="calheader">#IDAY#</th>'
        ,p_daily_open_format=>'<tr>'
        ,p_daily_close_format=>'</tr>'
        ,p_weekly_title_format=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<table cellspacing="0" cellpadding="0" border="0" summary="" class="t4WeekCalendarHolder">',
            '<tr>',
            '<td class="t4MonthTitle" id="test">#WTITLE#</td>',
            '</tr>',
            '<tr>',
            '<td>'))
        ,p_weekly_day_of_week_format=>'<th class="t4DayOfWeek">#IDAY#<br>#MM#/#DD#</th>'
        ,p_weekly_month_open_format=>'<table border="0" cellpadding="0" cellspacing="0" summary="0" class="t4WeekCalendar">'
        ,p_weekly_month_close_format=>'</table></td></tr></table>'
        ,p_weekly_day_open_format=>'<td class="t4Day" valign="top">'
        ,p_weekly_day_close_format=>'<br /></td>'
        ,p_weekly_today_open_format=>'<td class="t4Today" valign="top">'
        ,p_weekly_weekend_open_format=>'<td valign="top" class="t4NonDay">'
        ,p_weekly_weekend_close_format=>'<br /></td>'
        ,p_weekly_time_open_format=>'<th class="t4Hour">'
        ,p_weekly_time_close_format=>'<br /></th>'
        ,p_weekly_time_title_format=>'#TIME#'
        ,p_weekly_hour_open_format=>'<tr>'
        ,p_weekly_hour_close_format=>'</tr>'
        ,p_daily_day_of_week_format=>'<th class="t4DayOfWeek">#IDAY# #DD#/#MM#</th>'
        ,p_daily_month_title_format=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<table cellspacing="0" cellpadding="0" border="0" summary="" class="t4DayCalendarHolder"> <tr> <td class="t4MonthTitle">#IMONTH# #DD#, #YYYY#</td> </tr> <tr> <td>',
            ''))
        ,p_daily_month_open_format=>'<table border="0" cellpadding="2" cellspacing="0" summary="0" class="t4DayCalendar">'
        ,p_daily_month_close_format=>'</table></td> </tr> </table>'
        ,p_daily_day_open_format=>'<td valign="top" class="t4Day">'
        ,p_daily_day_close_format=>'<br /></td>'
        ,p_daily_today_open_format=>'<td valign="top" class="t4Today">'
        ,p_daily_time_open_format=>'<th class="t4Hour">'
        ,p_daily_time_close_format=>'<br /></th>'
        ,p_daily_time_title_format=>'#TIME#'
        ,p_daily_hour_open_format=>'<tr>'
        ,p_daily_hour_close_format=>'</tr>'
        ,p_month_data_format=>'#DAYS#'
        ,p_month_data_entry_format=>'#DATA#'
        ,p_theme_id=>101
        ,p_theme_class_id=>1
        ,p_reference_id=>15755243505117953
        );
end;
/
prompt --application/shared_components/user_interface/themes
begin
    wwv_flow_api.create_theme(
            p_id=>wwv_flow_api.id(20796405476320571)
        ,p_theme_id=>101
        ,p_theme_name=>'moj_blue_theme'
        ,p_ui_type_name=>'DESKTOP'
        ,p_navigation_type=>'T'
        ,p_nav_bar_type=>'NAVBAR'
        ,p_is_locked=>false
        ,p_default_page_template=>wwv_flow_api.id(20789318053320561)
        ,p_error_template=>wwv_flow_api.id(1621203972090849)
        ,p_printer_friendly_template=>wwv_flow_api.id(20789794214320561)
        ,p_breadcrumb_display_point=>'REGION_POSITION_01'
        ,p_sidebar_display_point=>'REGION_POSITION_02'
        ,p_login_template=>wwv_flow_api.id(20789214505320560)
        ,p_default_button_template=>wwv_flow_api.id(20790115155320563)
        ,p_default_region_template=>wwv_flow_api.id(20790696787320563)
        ,p_default_chart_template=>wwv_flow_api.id(20790696787320563)
        ,p_default_form_template=>wwv_flow_api.id(20790696787320563)
        ,p_default_reportr_template=>wwv_flow_api.id(20790622026320563)
        ,p_default_tabform_template=>wwv_flow_api.id(20790622026320563)
        ,p_default_wizard_template=>wwv_flow_api.id(20790696787320563)
        ,p_default_report_template=>wwv_flow_api.id(20794602676320568)
        ,p_default_label_template=>wwv_flow_api.id(20795102685320569)
        ,p_default_option_label=>wwv_flow_api.id(20795219193320569)
        ,p_default_required_label=>wwv_flow_api.id(20795415679320569)
        ,p_default_page_transition=>'NONE'
        ,p_default_popup_transition=>'NONE'
        ,p_file_prefix => nvl(wwv_flow_application_install.get_static_theme_file_prefix(101),'')
        ,p_css_file_urls=>'#IMAGE_PREFIX#legacy_ui/css/5.0#MIN#.css?v=#APEX_VERSION#'
        );
    wwv_flow_api.g_varchar2_table := wwv_flow_api.empty_varchar2_table;
    wwv_flow_api.g_varchar2_table(1) := '89504E470D0A1A0A0000000D494844520000007C000000500802000000541286A40000002C744558744372656174696F6E2054696D65005468752032352046656220323031302031363A31373A3135202D303030303E81E6C00000000774494D4507DA02';
    wwv_flow_api.g_varchar2_table(2) := '1A082C294F85D308000000097048597300000B1100000B11017F645F910000000467414D410000B18F0BFC610500001FD64944415478DAED7D59AC1D477A5E55F5DE7DF6BB2FE425AF2889A466B48C624B1E3B0323B1611B03D84F9907BFE4210112F8C1';
    wwv_flow_api.g_varchar2_table(3) := '4E908700C943803CF8256F01621888012FB0E141100CC680934C12C40E2C671C476392130D3594488A97775FCFDAA7F7AEF25F557DCEE93ECBE525399A81462C5DDDDBA74F752D7FFDF5FFDFBF5413FF8F3FFB8B7FF3EF7FE7AFF64E50C296CA968A1121';
    wwv_flow_api.g_varchar2_table(4) := '3848525B555482158C8338D909934BA696226429C48B93B398AE591AC61831C6103A09228A50CCD065DB4008C1B39A42144C28A3DD38ADE92AAF8411127F1862983F89620A97EC3488162C5DC5D88F134353E10E2104FAF5A278DB8F60306545F1280D93';
    wwv_flow_api.g_varchar2_table(5) := '0461D2D01578AA9752446949555D4A371D5323D098282CFB7F469183C87DCCEAE294D294C2E018FC82A1C147B8A9103E488431A3D44B68C25855579B616CAA3086B495A2CD46E38429355589E0014661789682459318C60FD4093D0F9A1493E5F7E10FB4';
    wwv_flow_api.g_varchar2_table(6) := '6BD4E67EE6D59754CBB2CF12F22B6FDD6C94ED073B877ECADC20DCAC96DAAEBFD5F303C6AED51AFF60B9FEE17E4B43E9ED76FFEF2C2EBE5DB56EED9C1C46C965DB5409FAD98D0D685121E8CF1EEE0309AECC37CE5CCF4D69D9D45F5FB26E1FB560E560C1';
    wwv_flow_api.g_varchar2_table(7) := '4C31264B55BA51123176191618A137AFD5EFEE1C76627A75A1BEDB713555F193F4C00DDF5E597877B10E93F18348D7D48A6D85717CD6764D4B730C581DA5E7F986A6DDFA64E7419018B84852562033CB7F1EAD0A961F5386162D689568C06C82D0A60E3C';
    wwv_flow_api.g_varchar2_table(8) := '837C3F0A28C334550D7DB3641984EC36BB5FD89C733DCFD08D8AA5DDD939F97B9717767A118C0D4692A6C9991F4B060036751574F527BE04F4872E829409FE84B9939D879F50CAEFA2DD44F9975FFD859B97168F9B2D9452C7B2E2386E75DD7EBF7FF7E1';
    wwv_flow_api.g_varchar2_table(9) := 'EECF7DE59D470F1EDC70D39B2FAD18A6616BB08AECF0B4DDA894BFFEAD3F478ABAB9BCB4B2340F2CF0733F1D00875AA6094BB1B5BB7FDC0BDF796DF3973A3D55550D55F5A30813D801187610F48214E5C3FB0F2EAFAEFCC4EBAF69AA5A6F5450924649BA';
    wwv_flow_api.g_varchar2_table(10) := 'BBBFF7BF6E7DFCA51BD7DE79FD15B7E79E9CB64D53D315A3527760376DEDEFA6A9BAB6B6D0EA7474C35075EDFEFFDFC6869A63E101EDC57E127F478B81E58613E466E2C24F92B5C5FA3B37366AD58A63D9406EAF1FB88117C741C9A9216057A6DED858D3';
    wwv_flow_api.g_varchar2_table(11) := '346D6B675BD39CF57A95124533D0EA9D7B572FAD56CAA5240A1F1C346F5EBB1AC609CC0E96EEE0F8F85FBF77EB1F5FBF5EB24A5D3F58AA97612FE88A72DAE9FCE6C3C7D0370C17AF974BDF7AEF3B776B4E3F8A31639592ED7940BEF4CA72FD3B8F0E897A';
    wwv_flow_api.g_varchar2_table(12) := 'EBF6C3DD57D717BB1FB8C0746110C2D8A1150282C5A794FAFDE0E0F6831DA06CD5D2DB7E54754C1017DB272D3FA187EDDE7CD90641022C9350A6AA4AC7F54C53D711EB45E949A777DC8D16EB9576D7D574584CD4EAF9BDC0DF05DE78B073DCEAD46CB3EB';
    wwv_flow_api.g_varchar2_table(13) := '05409A9E1FE9866AAB1A2CDA59D7BB7F78626A8AA9691F1C7415C72143BAE202D1F34B307E9F7FE617258C1E0438FEE858554E57ABF649BB77E4FA719A84349DD7F4945168FCBFFDF5DD9831906CC0EF846522C351D53FBFBBA560048CD24F69F56F3E44';
    wwv_flow_api.g_varchar2_table(14) := '868D358B72E14C572DE7838FBE6FA86605E49F574EE204A4EE71B76B42A79A89DFFBBFB77EED3FFCA70FDA2EEC8A6CF381508356811F811760E9C304012BC529A22CDBB1C399181ABF86AFE406871F580A2A5A810791B886D6A01D490EB8801DC8DBA1D9';
    wwv_flow_api.g_varchar2_table(15) := '75229E853A54340D4F11D1357F50FC1032A018C9848322A4A31415BA8A485E524B9A8ED37744742C6ECADD9E917D287970D69DBC0915688A52F849F805CB698BBC981A8A355941D391534196C347C5926C57C9BEE0B7A1CD69DA2F6F2C714E47AA4E9CD2';
    wwv_flow_api.g_varchar2_table(16) := '2F7E71B551712893FD0B8D971B544E160E65E560C34E555B2C939AB98131B1A729CABA98E4C1A9B7F16086633D6134BDEBA9141FEB054D6F70D6B79C76C0F49CF4B8A016F223CB69105563BA8D8B0380DB0F8F5A775A2E22AA0A838BB1F2CEE5955FFCE5';
    wwv_flow_api.g_varchar2_table(17) := '5F105B9C17D84A24E34B2C9B22E26F2C24A1645655B05842330A42CDE13AC9EEE457E2C1D184E12A652C5B152C9E1298017D1A258F65F0C49D8B179C511E49B886C7BF84892A18B321DB889A72C30FC9025AFAE5C393F7FFF4DB88285C0561BE32C656C7';
    wwv_flow_api.g_varchar2_table(18) := '03F0336F19371B202509A870378CEF9C74FC948292FCD26275B964C29D30A506608C283E09E24B652BE57009810EF99BE3F6BC6D9CF911547034F59D951A7C85258224E8DB072DC00975537F67A51E0B9102480B60E2774F7B0BB671EC85F4D9C8F14329';
    wwv_flow_api.g_varchar2_table(19) := '40B592A6FDE4720DF82C492926002B1908B920A5255D853BC05EB78FDAA0F060B2BD280172FDF46A032A8380FE3F7B67A07BFA20D353E60571DD3024D1014EA8C761F2E0AC031D006E6B76DD92AA003FC2CF43C08FBC031CFA01A0668D10900E2A26719A';
    wwv_flow_api.g_varchar2_table(20) := '1E86C943C04A829313D07C6EB0DDE9C338F8F6028C1886F015E85B79E7933E34C32C45E9B99E211E01FC02A2FBA3B6B7D351649D1F36252F5E9581DA54928053200238003802EC098CE11A5020CC11E4CE2337004D980A7D0BE40672C123555DF9F8B407';
    wwv_flow_api.g_varchar2_table(21) := '8A37E1ACC900728019026A4385E7B729EBB6DD83BBF701D881F07773142861AC0ABDD5A5ACA8401861098DE34CDA630CBB037819C493D477DFA079A18C1A0A972009431D595F5A0B182D10120B08359A9F10FD99F42910A92094D9D0DC427824D1F23572';
    wwv_flow_api.g_varchar2_table(22) := 'BD8FE683F3327BF0D5148433F6074700B7282BB680850ECFFA6F70B32A0695CB91046267546A02E6281C0900C6540DF3CC0BE19E0F02E0D1F6DE1FFFCFBFBEB777C2D274A0198A96C6D82D780E8C9B24840EF0B4C95021F7E137A56C72B643D1CA2BB021';
    wwv_flow_api.g_varchar2_table(23) := '55067826AFA5F2AD6309BBF1A89DAC0894C18A5D0C01CC2482CC57C88FA8F0EC68004222CBB5C5C20054E41D28B0398324117807EE118988581201712423C26D5D55B1D40320519C1A98B92BD5D2DB971AB8D7EDC2DDFD8383288A84F53E984D6E046272';
    wwv_flow_api.g_varchar2_table(24) := '19EC4168A850E41208E2326147C3252FF2E3A84A8E8F794985DD2C48CE0A7428CE79380449A3C1475E0819AC94E831BF218A3A1917D77ACAEA4FE8F0C20D10CB38EB4BD291A88AAA289C4A70074CC82008C2304C015916F648362F30A90CB0E0544DAE08';
    wwv_flow_api.g_varchar2_table(25) := '3CBEBAB202684595CDA549020F5F0445C8FAB2A624E2680566D467A3D5E0BF72ED644FB3DC128D917C78012386D912EE1B49E9003271F28BAF86B5590E8FB22209335F4A71AC787C078C06029F002722AE38B31D962409100A8B9140017BB05C2EDBB60D';
    wwv_flow_api.g_varchar2_table(26) := 'FCEAFB6071471C56E61A0C44E106B961E8BA9E114D8A53E8278CA249A2E70991BF26C260C9B3D818C986541E962165E553B3A83C7921362F2FF02030144C5BB6808B72860C8A2487BC22B9357B6277E34C3E516DC867C33B9CF155557607A407B2028981';
    wwv_flow_api.g_varchar2_table(27) := 'FA30C87C234058D775A19AE338C0E948121D9E8787A7F32BCB6B4F349CFF18B9C7A83C64605AA4321E97C85336D6F0BEE461218E38B1E96087C1E82737532A4A36E0C16A49A20CE922D76FF2D961BF9314985CAA610B72CA405FD9851C1890D5B22C20BD';
    wwv_flow_api.g_varchar2_table(28) := 'E779F0D5C87A820D9AA6703F6BA7D36E4B7F26B4311C52063172643A47848C647C4EF2CC7C2433C1679409AA9C2BBA9EA6E434C3A7510A2D0B87F0E4C82513C0FD4CBCA8DC07D673C34407E6C2C832756E62A5917055A5C072B6639B86F10323C18F7D99';
    wwv_flow_api.g_varchar2_table(29) := 'D88EB24802661019D64953550ACA00F4144220F6B97F56C53DD7150E160E36848BED05D19FAB48D99B8917E072B015334F4B66A3305694152F28FE9C05B87669BE012A46680042BEF1DFBFFD1B7FF81E2AEB8092B8798E26C25B2FCA7316EEF7C6F7FEDD';
    wwv_flow_api.g_varchar2_table(30) := '3FD9585D56E5470A042E593FB954B95C36FEF3D62922CA755BBBE78673866A13FC82C59FBFA48875D34C5864329D7BBE0CFDEAF2C2D77EE6E6D728D354AE07C0E2FA2F7FF9DD6FED75ABCA0B9E7FDE0212446199EB7BA04881E82567ABE3BF7F7F7F7365';
    wwv_flow_api.g_varchar2_table(31) := '0EBEE97941C93212452F9BA629C337597921779EA500A72B348BD24AA2336075A4EA6714FFD6F7F6BBDFDDE5F704A0BEA4AB966EF82FC4CB7317207A4232D78F203A439592FDEB5F583335E5399B7E51CE295E9C666E896EBB0DE46FF53C847F1CF879AA';
    wwv_flow_api.g_varchar2_table(32) := '35FFA974F3D4D63237FB1D53B70D5D95125AD7942849C6ACE4A946739AD22981C2A72B3CF986E4F444BE23685F780FB30CAB51B2D0C07F400B6EF75CA35C6282F24F745D1DFB6A96F52F1C4588BB6A2FDE17134116EE3449354D454F53A01F951B980345';
    wwv_flow_api.g_varchar2_table(33) := '2A1BCE7B7FE238F4021FAA495F395081FB0314621AF6DAD2A2A629CFC84F985367EFE824D7176A36CF1455E7F16E4A4DD3595F5EECF7BB7E18234C0DCD88D344414A92460A8F0EEAD5728514147BD688EBBA6767CDBEEBE98D5A9E07612EDD4E8B62FE0C';
    wwv_flow_api.g_varchar2_table(34) := 'C0872409154DC7C22F0676F7CAC202A351A7D7076A689A91243118E2491A638DA844AF55AA93FEB576BBD56AB5BD28A9D6CAEC29E38C925B67AE15CCBFD7ED2609CFF45375250812DD3092385E5FBDCC866952CF40F3290F62A2E066EB14985433CC05CD';
    wwv_flow_api.g_varchar2_table(35) := 'E4994C9804810FB5A330B66C0BF8CAEDBB382486EE949D9220FA5833D8F7FD4EA79DB0296A495149F3F42C8E53DB76188BDDE6996E58491C2D2DAE3001E3A22864888661A49B3C1DC8EDF719419A62949DB2A290C2F01983D5ED76BB8A6EE1A9B3B90811';
    wwv_flow_api.g_varchar2_table(36) := 'A41BC00DC2B8E8051E0B1E0D81228FFB503626879E36CE8B0952729E9C41E48DB724C417FFB6E0AD1C7C10EB4D2737194F3B080360114DD34C539F1E12011A618194077391E245E5E2652C1172E83A1F97DBF0195617686D188626325E2F386B3968C7E4';
    wwv_flow_api.g_varchar2_table(37) := '912435DFD9D0A555080CA17C15FE0708C2A54DE6BDC47C2BA8CA533A4D0BEA6ED4119763781064CDCFB598C332AD2FCB32A5289EE51667C3FF067311E10FDAF5FBD9ED9C679B07D954A51F8668E4A21749A0BAA69BBA1F47F0E3C56121D5251F4DCEFF16';
    wwv_flow_api.g_varchar2_table(38) := '613FC73086E3191847A05BD3A4D9EC58B60D0405810E169463DA7112E361184C48764DD3CB96D1EBF7971697184D9BCD56B3E7566AD5A7840DB3BDF3CF8A3D9EC1230704E907C1EFBFFF30665A4927DD289519D2D0D01B0DEDE6FAFC1FDDD9B50DC356C8';
    wwv_flow_api.g_varchar2_table(39) := 'A91FC137719A7E75B3AE68E4BFDE6FC272990AEEF3E00A835F9A421C95C7497455E9FA91ADABA08A60E500260629D5D3E09FFEDDD760BD0A4417FA2DED745B4043D86E41D24758AB952BB00C956A194439C83B3FF0E7E797D657D678DC0C540D2120165B';
    wwv_flow_api.g_varchar2_table(40) := 'AD6614D3CFAA898A45B4A07346A836375FBDE46894A51D378869EA77711257CC5E538BECD5F94A5DA7A6AE6D1D9D86BEAE244AC5EF9C7AC98D8D25A81A8551487055578E3ADEA5851A45E9A50A173B1FED9E546A35B07D12863E7C7448E9CD51B779999E';
    wwv_flow_api.g_varchar2_table(41) := '89B69CC81BC6E764C009C411C85C117FE482C817B17011A6B29F3565ED474B731CC5D1AD8F1E2409E5CA7990C30333BDBC343F5FAFDEFEE8214FE419A46B803ABBBEB106DC76F7936D6D985E81327987B3FC6B94658664BFB8A0B40CEDCD57AE01DD00A7';
    wwv_flow_api.g_varchar2_table(42) := '03FB0F88EE0793387D56190AC6C970ED67B1709C9ECD6BB463651EC910BA0CD5AB38A7814406D1F8418429BA38473169DF942C73A4480D5DC713E0F7F35C382DA6F944C8B35249E614E99A0662238B9156CB25457D3AFBEA457986C2D363C2B06091FED5';
    wwv_flow_api.g_varchar2_table(43) := 'EDEF3FD83D52877B671AFE9E942378540F9F2F6846601B8D83FB8B88A72C67910DB3F1CECF2B98DE289B5A130D0E2E3CB76AC2C5740709F5BFFA95B76B95D270C405EE5E5A9EC7A60E5B40D23CA5A9A2A885547D8E9FE998E007B5C3459300C83CD96FF6';
    wwv_flow_api.g_varchar2_table(44) := '78529EFE8815551159324CA4086533A622DFFEFCC2D13BEC4D55E5998F293F2E02404BE48C4D7956B6AFC8B0D779E3C772FCD0D430DBEC79B018B4C665C640E7894C72A41B7ABE4E81E8A6861EDFBB6B97CA0056DE78EBADC39D4707272DC356E388A661';
    wwv_flow_api.g_varchar2_table(45) := '0403ABCECF5FB972B96831D1FBF73E248A8911ED757BD75F7F0334C58C8417DCEFB41F3FDE4919D7468EA51D9FB60105C3174BEB97565716E9F97E0C8C93D0BF77F72ED89DBAAAD71BB593C363C5D037AFBD5A293B631B0CD672E7D12767CD0ECF3C5471';
    wwv_flow_api.g_varchar2_table(46) := '0C005B8E7F61E1CAC6A5826786E05EF36C7B1B46952441B47A75736579813E6BE63670F9837B1F763A2E3F98A4D0C6C2CAD5AB1BC0516AD181C3D10BFCB11D0738E8A8DD3D6EB6B81387E072B90CD207641058666992CAFC4745D5001D16068D90E7F5A9';
    wwv_flow_api.g_varchar2_table(47) := 'C446943AA5D2AC4C0D2C40B1E779C00B3CED4653033F2022615537A13C21A94620D7B4DFEF83AD0FE041D3F5388A788FE50A988E93767F1080CD185371BA2C499299E317A3E2A2368E011B1A60D73E6924E70FD2E3234C249A1976B75AAF1A2202EA7B5E';
    wwv_flow_api.g_varchar2_table(48) := '144505A2A782DC827DF97F62F36739C732C93633B28BDB0FF4F2F07A345CE92229E22691039B2551E673C8476E009C9D4C9A626DB12C7D70F4198D7C156CA22F4286F81A9F37FE2CD77900C5D9C0658086A83D73035CD0E0C58344E3616E2CDC04B690CD';
    wwv_flow_api.g_varchar2_table(49) := '4BA217C40B8EC2CE5FBE472CBBFAE52F23A2F4DCAE4200E2C0EEA4F56A6D960FDD757B4864B48A640E8E64A157E044DBB2C66A06BE1752AA7198C41D7BAAA6B33481C601BC4A12722B8C80CCD0A776C46DE64E8B278373FF0F11661A5096F1CCA89C6743';
    wwv_flow_api.g_varchar2_table(50) := '16EE381474066B3AA6EC9CF17BFD9E1F840AE7C4CCC7079A00348269E83C4F82EF4B0576A76DDB17A079B670878787202A4AA5D2D40A05A24741F0F19F7C535B5E79F3DD77C1EAEC763A419C86610F28512A95B569A9628CA5ED4E8BAF6100B242B36DB3';
    wwv_flow_api.g_varchar2_table(51) := 'EFF52BE52A457492E850B9D56EF67AAE6118711C61A2D5ABE576A70BDF8471A46B3A61A8B1B8348BE849129F354F292560D3F11C66C6345D03FB11986492E87BFB7B3CA061C2783A6CF6F839D13DF7F0F81896D136EC94C620831CCBEAB97D7ED8398A74';
    wwv_flow_api.g_varchar2_table(52) := '138417003A7DF3CAC6059D7AC03A30416B62FAC352102F002AFACD2606B3BE5EBFE0AAFEA0CA0FFC8C1DCF877D6E73EF073EAA29E2A513469153864EDCAE3B76467424E4C60B1EE0F4E2819D694EBFB1631263883D97943D43780EDDEEA35E86C461E775';
    wwv_flow_api.g_varchar2_table(53) := '95AB38B555943F852B9540AE7D09496753606A8BD2E79AD56F941C2D170C29103D74FBFDF7DF47A6895EFB0268D4300CC4511392A4A9C653F0A699AC8CF1100FEF834A1F8F2C209DC684208C23F0FB2903535805110975344D038901A259D775917486CF';
    wwv_flow_api.g_varchar2_table(54) := 'B78A41A603CC802149E70F57F5FCB00FE013C0C1FA183D5211F4823661F03CE4C6386C5727DAE7D10F1EA2E219FA499C8843DB2AA801781A3A04CD0406816E184C9C22D7B805F3E4C25F9CD1EDD8B643E4A818AA39547A16A6103DF1FDD33FF9065E5898';
    wwv_flow_api.g_varchar2_table(55) := 'BBF91AD0A8DD3C753D2F89121073E55279656D0D4D203398F9D9E991EFA595AA1D84B169689EEFD97619241A297AD8F91B4D02AFDB73D394133AE5A10F4D57D58EDB752C07369DED542F5FBEC48A6788F27D4520D3CF4E802C20FC431FB42305990EE435';
    wwv_flow_api.g_varchar2_table(56) := 'EDAA353F9F3BAAC0396D776F5BBC1DC2F0F939CB0430A96D9756565626C025A1697C7A7A0A4A85A79573FDC9F12BCCBAE7F6161697E338F08E3CE011787C7565F58951031E5EA674A1522E572BC73D6F605A16236D79997ED06C1F3DDE5634D5595C92C2';
    wwv_flow_api.g_varchar2_table(57) := '45AEB0745ACE12916C902D06D30613F0897250023711FB8E81B5E57935B8D634ED890274389EE1C18CE1D98EB19A491C23713A828A8365AA969D3999D5AC082266F03067CA17FCB5E882F29DC93723E074100E5B6B540CB1C9A6C8F446A55C7FEB75BE73';
    wwv_flow_api.g_varchar2_table(58) := 'C501515C0C630EAEC783B185C5C885B6A6B044C14D23DB4285F69FC845D3DEA520B322A675353A7D320807CEB095478D0D289D6F24677C5C88E8A3585BA6A7C6805381E83AA3FE07773140EC57AF2321EC388CE38818507822DD929A368EE7C070E55B9B';
    wwv_flow_api.g_varchar2_table(59) := '9B04441A45327165CCE180B85F220196D234559CDAA2D2DD3174938997C19CCF4A4C9E4613F3E1A1159916A0285CE34CF4C5F98C3BC419E33A499F298EC15460033A0BEB0BC7E2241111662D6C01C33015E5E972DFCE4E4F2DDB9E05EDC714A9FBE0EB5F';
    wwv_flow_api.g_varchar2_table(60) := '576AB51BFFFC1A5195475B0F417EF2B70DF9619C06BA6600127FF9A5973575C4DA00BDF70E76414579BE4BB06EDB46D7ED827604EBFCDA95AB639DB9DD4EDB7583D0039A0304B64D07CC1000F858BC798751E5DA4B2FEBEACCE94561B0B3BB83910A0301';
    wwv_flow_api.g_varchar2_table(61) := '239F7380AE224C9D527D797E7EACF2E1E17EAB0306810A84D34CE395975E9D851F7BDDF6D15933887D5DD180DC20D1DD9E673986A680FE04D38D2D2DAFD62BD58B533C113A5C9F616DA031994E93A4F9F831E8F8C6C6061A08D0818C1E448B2624BBAC20';
    wwv_flow_api.g_varchar2_table(62) := 'CF884A9CC010CBCBDB7C11EA8EE5025C288C4279C612B8D834671A14595F52A66324BD8C83A32378465D2676031615CFDB43347B290D162E1A998FCFA2288605B838AD276932797F8AEFC50D3952E18E8A08761CCFF861684A3A4DD1952A0FB6B2C151FB';
    wwv_flow_api.g_varchar2_table(63) := 'D1C5988FB4807F8BB7B2A7848764AA9F76581763CC866B963B433FF6D4B0AF2C1FA8E0DF9E687602D20FCE6F0FA4F9ECC7CF1B271B6564552D5346F8A628D23EE0F4FBF7B1A6B34B97F87B60A24802D88CBA5399459CD696DEF031DD35896A41350C26C9';
    wwv_flow_api.g_varchar2_table(64) := '24F7A5492ACEDBF3BE60908A3893399B7DA880D27C5749EB433AAAE491DDF1CA624F801CE3581E656FADC1D3BCFDD0356F41E53847A81602C8476A29B82FED89ECB4E6C54C5C78C4EBF500772ABA2119C2317485CCC2E9FDBEFFA7DF64A5B2F50FFF1108';
    wwv_flow_api.g_varchar2_table(65) := '994EEBCCEDBB824CC4B49CD5D5553671109FB2F4E8688F20C50F3C053A3134FEA6BA38B54BE52B57AEE6EBC388DD7EF7B4D90CE34025AA907A86EF7A228DD5A0691427E9FCE272A3DE980AD5394E8FC2A3C3432A9659C11AA813D0A000CC6CA7BEB4B030';
    wwv_flow_api.g_varchar2_table(66) := '86D3F78F767A6E90A611984EA601ED4365637DFDD218CE0612F743EFACD94C522034101D70BA057A2C0E6398FBE2D24A1CF961100100B02C676D75ED42383D491A965EADD54EFAFE6077CFC6E9FBA7CDFDEF7D4FD1F5CA4BD7445A4B8C86D2539A73E3C6';
    wwv_flow_api.g_varchar2_table(67) := '3612D820E156A20806A91C99A472D38ED7E74DA5D216C5F295290049797C07856124DC434C9E379FBE89C5668716E490246E1C26F70A5D52A81CF3B7FD88703ECBFCB3223CA44E6D56B236B0BB94E9C0E0D2352DF3321271165B1C7F579E9C42281AD4F9';
    wwv_flow_api.g_varchar2_table(68) := '9B3108AC1512C26FBD51CDE3F402D1FB519C8A39739C3EF686887C3ADAC4DAE61721BFCB66D4CD571BF86D7260F85C361A3E95AB3C4DF38CF94F465567363B1A55CEB73F4A9B9BFDF8D41649DEF7520651334BA63BAA121F1C70165D5A42D2957C017CCA';
    wwv_flow_api.g_varchar2_table(69) := '772595E235FFF6093C0B300C7298908C4F3E150416CFA2819B67F0D28651D7CF52F8BBDC10965E4969EB3E739EC5B0F47A5D30718C1960AC88D33B9D4F7EFBB7945AFDDA6FFC33A292EDDDC76198C0DE374C956F9680BEF2CA35BD98090F82F5F1F62330';
    wwv_flow_api.g_varchar2_table(70) := '508228D0551DE0B6DBEF11459F9B9B5B9A5F18EBACDD39EB76DC288E6DCBEAB81D4A938ED75B9A5FF5DD2E53158368EBEB1BF66C941685FEEEFE1E10055A5079742555854C2F951BCB137DEDEE6D814C8F9350D30CCB30529A00BCDF58BF3CD96CF3ECE4';
    wwv_flow_api.g_varchar2_table(71) := 'E8A40935419A3BB6D5E9F500C272994900BF69A0B337AF6C6ADA532C03E0F45ECF050ACCAA500CD785E1DE9D3B8A61ACBDF9860CEEF0F70D21E935242CA5E634C77C1C73ED4F85B096015521D01561A08E8D2616315FA0B09AF0B76366388FC78044EA26';
    wwv_flow_api.g_varchar2_table(72) := 'A8D6596FFE934B2CDFEA21AD4D9E58A01069094F6E9730F4A1964CD192008E70CB758A5D4A854352EE3C42B2779CC0BA02E985CF0E1913119227166872AAC7748A4CE7EF4792AE2BA13A70D1C59109DE89A32F637EF4917F7CACE6C8418F87487A787F14';
    wwv_flow_api.g_varchar2_table(73) := '989C75B426EF71197B5DC7F4BEC6325072B5D158CD899B998F2F1BECD459A3194D49D1944F9E86A74D4D951A7F5A10A3EF837DF4E9BD9FE3C7BB004B74FDFEF6694766D70E4F14552DFDDD9737F3EE8D6260FA1CF5F7A23CA9A80A01CCFDFFEE9F8218AB';
    wwv_flow_api.g_varchar2_table(74) := '591A7F6B0B635D3F3450F053AF6C166A4E3EFC82ECCF5840B6D0F4746FCB00D45275144C00D5296144F4717055B4485300E8174D98FE4C97013047437364E8A2CA5DE4D21C2F700170607D71F1577FFEA7F0E075FE52B25BA6C1ADE5596E008552354D3F';
    wwv_flow_api.g_varchar2_table(75) := '0744E781D358444441EF7901A02F62EA6AFE02E4B2212F14C24FB184D9453F8C408CE8E28EA6102DBB005C44C4194C5CB74BFC9DA439671F4668ECE463318174AE063F3F6A8AFC308AE787805981C45B87CDFFF8FED6D58AF9F337D67EF7D6E3CB25E3EF';
    wwv_flow_api.g_varchar2_table(76) := '5F5FFBEDF7B75EAD993FFBCAEAEFDDDABE5A36BEF2F2CAEFDFDABE5635BFBCB9F407B7B76FD4ADB73716FFF00EBFF8D2C6C21FDDD97EAD61BF7169FE8F3FD8A96AEAA281DD84F553D4E5FF960309133A67907F75656D26D13F57258C22453702DF8B5B47';
    wwv_flow_api.g_varchar2_table(77) := '5EA4B9BD4AE7683F08AD5EB75C0D3B49AB0B17A47B1AC44AAFE754E35ED0ECF416ECF6E1CEB16FF51A663DE9F9AD5EB766D4A36ED0763B1535EE9CAC2FD7F70F9A21D6DEDC5C713DD84BD15EAFDB6AFA6387303FA744A78C82990EA2B7EAD8D7E74A7315';
    wwv_flow_api.g_varchar2_table(78) := '67BE5EF9E24265B15A5AAC571B3A5999AB2C362AAB8EBE36575DAC571C4C2FCDD7E1E2C65CF9CA5263A951B1105D9F9F5B9EAB9898AECDCDADCCD52E5B5ADFF35FDB587183F0E0F85411CEB2AAA15E9D5F1EF32B64C691E3389FB79318543870C80F5781';
    wwv_flow_api.g_varchar2_table(79) := '154E623C8FC3E8335A08F9514C599EC49058E52FBE739732A4BC38EBF5A9942CBED80FA22FBEB43A572D714E075ADFFEFED6BFF8E66D54D29FBB8317654611AF12B9FF6F7F157658F6B63A153364E2575FD0FC532B29E3EFFF96126D70BA2E4D90E785E4';
    wwv_flow_api.g_varchar2_table(80) := 'A97D989FADC2DF2D93504D1EC315BF1379524204B823CA4C823DCA74E1D14D1123085B84BFDB9F08239382794948208230E223FF671492DCA1E994324D64702818F98C59181BE21FD090FFD4C282A6145EFBCA6882FC20D23EC3079F2F525CCABE767DAD';
    wwv_flow_api.g_varchar2_table(81) := '1382C982749580DD68F2402EF3D3D8C21A5895A7BD60A16AF582585530FFB72F083AEB862B73253F101903981DB5DCD5C55AF611D1304A2DDB48E284071B1455B1D5AE1BA90485715A73AC9E1FED37DD4A557754AD6AEAFFFBE33DE9831669953C2813A1';
    wwv_flow_api.g_varchar2_table(82) := '7BA7FBEB173DE1F1592DC096D7966C4B551036752DA469D53480B85B67AD248CD6E61A60D9CF55AD0560673F2899C649BF7FD60236A5F35547D714B030BB1D6059BA203E1A0ADEDA3D59AC35501A63CA42374C35B2522FC9C3EC40F4C3A677827A711252';
    wwv_flow_api.g_varchar2_table(83) := '8500973F3868656F76E9B6DBFC0D031F7DF2F127DBEA5366EC7D160B2DA6A90E65834C50C0139EAFFC2B55E411BDDC4794736DF1BCC9B137CB2B22BD6018FFD175ED2BEFBE5572ACCC3882CFE3590C9FEB32EB5D0B177F0A15331EB23B213F6549FF161A';
    wwv_flow_api.g_varchar2_table(84) := 'A5A93C6C6BF25C0000000049454E44AE426082';
    wwv_flow_api.create_theme_image(
            p_id=>wwv_flow_api.id(20796405476320571)
        ,p_theme_id=>101
        ,p_varchar2_table=>wwv_flow_api.g_varchar2_table
        );
end;
/
prompt --application/shared_components/user_interface/theme_style
begin
    null;
end;
/
prompt --application/shared_components/user_interface/theme_files
begin
    null;
end;
/
prompt --application/shared_components/user_interface/theme_display_points
begin
    null;
end;
/
prompt --application/shared_components/user_interface/template_opt_groups
begin
    null;
end;
/
prompt --application/shared_components/user_interface/template_options
begin
    null;
end;
/
prompt --application/shared_components/logic/build_options
begin
    null;
end;
/
prompt --application/shared_components/globalization/language
begin
    null;
end;
/
prompt --application/shared_components/globalization/translations
begin
    null;
end;
/
prompt --application/shared_components/globalization/messages
begin
    null;
end;
/
prompt --application/shared_components/globalization/dyntranslations
begin
    null;
end;
/
prompt --application/shared_components/user_interface/shortcuts
begin
    wwv_flow_api.create_shortcut(
            p_id=>wwv_flow_api.id(11788016681546864)
        ,p_shortcut_name=>'DELETE_CONFIRM_MSG'
        ,p_shortcut_type=>'TEXT_ESCAPE_JS'
        ,p_shortcut=>'Would you like to perform this delete action?'
        );
end;
/
prompt --application/shared_components/security/authentications
begin
    wwv_flow_api.create_authentication(
            p_id=>wwv_flow_api.id(15975057597201081)
        ,p_name=>'ATH_EOR'
        ,p_scheme_type=>'NATIVE_CUSTOM'
        ,p_attribute_03=>'authentication_pkg.authenicate_user'
        ,p_attribute_05=>'Y'
        ,p_attribute_15=>'15494839981941314'
        ,p_plsql_code=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'procedure post_auth_15494839981941314 is',
            'begin',
            'authentication_pkg.post_authentication;',
            'end;'))
        ,p_invalid_session_type=>'URL'
        ,p_invalid_session_url=>'f?p=EORSEC010:SEC010_LANDING'
        ,p_logout_url=>'f?p=EORSEC040:SEC040_LANDING:&SESSION.::::P10_SESSION_EXPIRED:N'
        ,p_post_auth_process=>'post_auth_15494839981941314'
        ,p_cookie_name=>'EOR'
        ,p_use_secure_cookie_yn=>'N'
        ,p_ras_mode=>0
        ,p_reference_id=>15494839981941314
        ,p_comments=>'EOR Custom Auth Scheme'
        );
end;
/
prompt --application/ui_types
begin
    null;
end;
/
prompt --application/shared_components/plugins/region_type/com_sungard_collection_shuttle_region
begin
    wwv_flow_api.create_plugin(
            p_id=>wwv_flow_api.id(14926818107564621)
        ,p_plugin_type=>'REGION TYPE'
        ,p_name=>'COM.SUNGARD.COLLECTION_SHUTTLE_REGION'
        ,p_display_name=>'Collection Shuttle Region'
        ,p_supported_ui_types=>'DESKTOP'
        ,p_image_prefix => nvl(wwv_flow_application_install.get_static_plugin_file_prefix('REGION TYPE','COM.SUNGARD.COLLECTION_SHUTTLE_REGION'),'')
        ,p_render_function=>'collection_shuttle_region.render_region'
        ,p_ajax_function=>'collection_shuttle_region.ajax'
        ,p_substitute_attributes=>true
        ,p_reference_id=>12931001263164850
        ,p_subscribe_plugin_settings=>true
        ,p_help_text=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<p>',
            '	This region plug-in creates a region that works in a similar manner to a shuttle item, but using an Apex collection to hold the data rather than a deimited string.&nbsp; The Apex collection needs to be populated prior to rendering the region as foll'
                ||'ows:</p>',
            '<p>',
            '	c001 - key value (not displayed)</p>',
            '<p>',
            '	c002 - display value</p>',
            '<p>',
            '	c003 - set to null for &quot;unselected&quot;, any non-null value e.g. &#39;Y&#39; for &quot;selected&quot;</p>',
            '<p>',
            '	c004 - leave null (used by the shuttle to indicate whether it has changed the row)</p>',
            '<p>',
            '	On&nbsp;Save processing&nbsp;the Apex collection must be queried to determine which rows have been changed (c004 = &#39;Y&#39;) and base data must be inserted or deleted as appropriate.</p>',
            ''))
        ,p_version_identifier=>'1.0'
        );
    wwv_flow_api.create_plugin_attribute(
            p_id=>wwv_flow_api.id(1956881883825281)
        ,p_plugin_id=>wwv_flow_api.id(14926818107564621)
        ,p_attribute_scope=>'COMPONENT'
        ,p_attribute_sequence=>1
        ,p_display_sequence=>10
        ,p_prompt=>'Apex Collection Name'
        ,p_attribute_type=>'TEXT'
        ,p_is_required=>true
        ,p_display_length=>30
        ,p_max_length=>255
        ,p_is_translatable=>false
        ,p_help_text=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'The name of the Apex Collection that is maintained by this region.  The collection must be pre-populated with data as follows:',
            'c001 = key value',
            'c002 = display value',
            'c003 = null for unselected, not null for selected',
            'c004 = null (this will be set to a non-null value by the shuttle on rows it has changed)'))
        );
    wwv_flow_api.create_plugin_attribute(
            p_id=>wwv_flow_api.id(1956952753825281)
        ,p_plugin_id=>wwv_flow_api.id(14926818107564621)
        ,p_attribute_scope=>'COMPONENT'
        ,p_attribute_sequence=>2
        ,p_display_sequence=>20
        ,p_prompt=>'Number of rows'
        ,p_attribute_type=>'INTEGER'
        ,p_is_required=>false
        ,p_display_length=>2
        ,p_max_length=>2
        ,p_is_translatable=>false
        ,p_help_text=>'Number of rows to display on each side of the shuttle.'
        );
    wwv_flow_api.create_plugin_attribute(
            p_id=>wwv_flow_api.id(1957008690825281)
        ,p_plugin_id=>wwv_flow_api.id(14926818107564621)
        ,p_attribute_scope=>'COMPONENT'
        ,p_attribute_sequence=>3
        ,p_display_sequence=>30
        ,p_prompt=>'Allow filtering'
        ,p_attribute_type=>'CHECKBOX'
        ,p_is_required=>false
        ,p_default_value=>'Y'
        ,p_is_translatable=>false
        ,p_help_text=>'Determines whether a filter item is displayed above the data on each side.'
        );
    wwv_flow_api.create_plugin_attribute(
            p_id=>wwv_flow_api.id(1957162587825281)
        ,p_plugin_id=>wwv_flow_api.id(14926818107564621)
        ,p_attribute_scope=>'COMPONENT'
        ,p_attribute_sequence=>4
        ,p_display_sequence=>40
        ,p_prompt=>'Show Add/Remove All buttons'
        ,p_attribute_type=>'CHECKBOX'
        ,p_is_required=>false
        ,p_default_value=>'Y'
        ,p_is_translatable=>false
        ,p_help_text=>'Determines whether Select All and Remove All buttons are available on the shuttle.'
        );
    wwv_flow_api.create_plugin_attribute(
            p_id=>wwv_flow_api.id(1957247884825281)
        ,p_plugin_id=>wwv_flow_api.id(14926818107564621)
        ,p_attribute_scope=>'COMPONENT'
        ,p_attribute_sequence=>5
        ,p_display_sequence=>50
        ,p_prompt=>'Each select list width'
        ,p_attribute_type=>'INTEGER'
        ,p_is_required=>false
        ,p_default_value=>'20'
        ,p_is_translatable=>false
        );
end;
/
prompt --application/user_interfaces
begin
    wwv_flow_api.create_user_interface(
            p_id=>wwv_flow_api.id(74312104640072)
        ,p_ui_type_name=>'DESKTOP'
        ,p_display_name=>'Desktop'
        ,p_display_seq=>10
        ,p_use_auto_detect=>true
        ,p_is_default=>true
        ,p_theme_id=>101
        ,p_home_url=>'f?p=&APP_ID.:10:&SESSION.'
        ,p_global_page_id=>0
        ,p_nav_list_template_options=>'#DEFAULT#'
        ,p_include_legacy_javascript=>true
        ,p_include_jquery_migrate=>true
        ,p_nav_bar_type=>'NAVBAR'
        ,p_nav_bar_template_options=>'#DEFAULT#'
        );
end;
/
prompt --application/user_interfaces/combined_files
begin
    null;
end;
/
prompt --application/pages/page_00000
begin
    wwv_flow_api.create_page(
            p_id=>0
        ,p_user_interface_id=>wwv_flow_api.id(74312104640072)
        ,p_name=>'0'
        ,p_page_mode=>'NORMAL'
        ,p_step_title=>'0'
        ,p_step_sub_title_type=>'TEXT_WITH_SUBSTITUTIONS'
        ,p_first_item=>'NO_FIRST_ITEM'
        ,p_step_template=>wwv_flow_api.id(20789318053320561)
        ,p_page_template_options=>'#DEFAULT#'
        ,p_dialog_chained=>'Y'
        ,p_overwrite_navigation_list=>'N'
        ,p_nav_list_template_options=>'#DEFAULT#'
        ,p_page_is_public_y_n=>'N'
        ,p_cache_mode=>'NOCACHE'
        ,p_last_updated_by=>'CHRISQUINN'
        ,p_last_upd_yyyymmddhh24miss=>'20110228115831'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(5239055396194975)
        ,p_plug_name=>'Hidden'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_region_attributes=>'class="hiddenregion"'
        ,p_plug_display_sequence=>900
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BEFORE_FOOTER'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(8078621331000524)
        ,p_plug_name=>'GlobalDynamicHidden'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_display_sequence=>910
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'REGION_POSITION_04'
        ,p_plug_item_display_point=>'BELOW'
        ,p_plug_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  app_ui_components_pkg.Global_Dynamic_Hidden;',
            'end;'))
        ,p_plug_source_type=>'NATIVE_PLSQL'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(8659002857618270)
        ,p_plug_name=>'SystemModeBanner'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_display_sequence=>920
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'REGION_POSITION_01'
        ,p_plug_item_display_point=>'BELOW'
        ,p_plug_source=>'app_ui_components_pkg.System_Mode_Banner;'
        ,p_plug_source_type=>'NATIVE_PLSQL'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(13366931356188927)
        ,p_plug_name=>'Main Menu'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_display_sequence=>10
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'REGION_POSITION_08'
        ,p_plug_item_display_point=>'BELOW'
        ,p_plug_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  app_ui_components_pkg.mainmenu_render;',
            'end;'))
        ,p_plug_source_type=>'NATIVE_PLSQL'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows=>15
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_plug_display_when_condition=>'101'
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(13367631150273996)
        ,p_plug_name=>'Breadcrumb'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_display_sequence=>20
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'REGION_POSITION_06'
        ,p_plug_item_display_point=>'BELOW'
        ,p_plug_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  app_ui_components_pkg.breadcrumbs_render;',
            'end;'))
        ,p_plug_source_type=>'NATIVE_PLSQL'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows=>15
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_plug_display_when_condition=>'101'
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(13368946808533955)
        ,p_plug_name=>'Header Buttons'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(15557072664074263)
        ,p_plug_display_sequence=>30
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'REGION_POSITION_07'
        ,p_plug_item_display_point=>'BELOW'
        ,p_plug_query_row_template=>1
        ,p_plug_display_when_condition=>'101'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2265926135699818)
        ,p_name=>'P0_CONTROLLER'
        ,p_item_sequence=>60
        ,p_item_plug_id=>wwv_flow_api.id(5239055396194975)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>4000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2266129598700832)
        ,p_name=>'P0_OWNER'
        ,p_item_sequence=>70
        ,p_item_plug_id=>wwv_flow_api.id(5239055396194975)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>4000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(5763105261685850)
        ,p_name=>'P0_BOILERPLATE_ACCESS'
        ,p_item_sequence=>20
        ,p_item_plug_id=>wwv_flow_api.id(5239055396194975)
        ,p_prompt=>'RBAC Boilerplate Access'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6855120624573346)
        ,p_name=>'P0_READONLY'
        ,p_item_sequence=>10
        ,p_item_plug_id=>wwv_flow_api.id(5239055396194975)
        ,p_prompt=>'Readonly'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(8331512361632169)
        ,p_name=>'P0_CMS_OFFENDER'
        ,p_item_sequence=>30
        ,p_item_plug_id=>wwv_flow_api.id(5239055396194975)
        ,p_prompt=>'Cms Offender'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(11545227350310869)
        ,p_name=>'P0_LAO_RESTRICTED'
        ,p_item_sequence=>40
        ,p_item_plug_id=>wwv_flow_api.id(5239055396194975)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(11545711336325158)
        ,p_name=>'P0_ACCESS_MODE'
        ,p_item_sequence=>50
        ,p_item_plug_id=>wwv_flow_api.id(5239055396194975)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>4000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'Y'
        );
end;
/
prompt --application/pages/page_00002
begin
    wwv_flow_api.create_page(
            p_id=>2
        ,p_user_interface_id=>wwv_flow_api.id(74312104640072)
        ,p_name=>'Terminate Previous Order'
        ,p_alias=>'MOD500'
        ,p_page_mode=>'NORMAL'
        ,p_step_title=>'Terminate Previous Order'
        ,p_step_sub_title_type=>'TEXT_WITH_SUBSTITUTIONS'
        ,p_first_item=>'NO_FIRST_ITEM'
        ,p_group_id=>wwv_flow_api.id(6767457294623073)
        ,p_page_template_options=>'#DEFAULT#'
        ,p_dialog_chained=>'Y'
        ,p_overwrite_navigation_list=>'N'
        ,p_page_is_public_y_n=>'N'
        ,p_cache_mode=>'NOCACHE'
        ,p_cache_timeout_seconds=>21600
        ,p_help_text=>'No help is available for this page.'
        ,p_last_updated_by=>'PAULA'
        ,p_last_upd_yyyymmddhh24miss=>'20181213080112'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(8666613940647818)
        ,p_plug_name=>'Terminate Previous Order'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_escape_on_http_output=>'Y'
        ,p_plug_template=>wwv_flow_api.id(20790696787320563)
        ,p_plug_display_sequence=>10
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_display_point=>'BODY'
        ,p_plug_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'The last OASys assessment was completed on &P2_DATE_COMPLETED..',
            '<p>',
            'Do you wish to start a new period of supervision?',
            '<p>',
            '<p>',
            '<p>',
            'Caution: Do not undertake if the previous assessment relates to a current sentence.'))
        ,p_plug_query_row_template=>1
        ,p_plug_query_num_rows=>15
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'Y'
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(8687445897615342)
        ,p_button_sequence=>20
        ,p_button_plug_id=>wwv_flow_api.id(8666613940647818)
        ,p_button_name=>'P2_YES'
        ,p_button_static_id=>'P2_YES'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Yes'
        ,p_button_position=>'BOTTOM'
        ,p_button_alignment=>'LEFT'
        ,p_grid_new_grid=>false
        ,p_grid_new_row=>'N'
        ,p_grid_new_column=>'N'
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(8687872617618585)
        ,p_button_sequence=>30
        ,p_button_plug_id=>wwv_flow_api.id(8666613940647818)
        ,p_button_name=>'P2_NO'
        ,p_button_static_id=>'P2_YES'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'No'
        ,p_button_position=>'BOTTOM'
        ,p_button_alignment=>'LEFT'
        ,p_grid_new_grid=>true
        ,p_grid_new_row=>'N'
        ,p_grid_new_column=>'Y'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(8694968626830403)
        ,p_branch_name=>'No'
        ,p_branch_action=>'return app_navigation_pkg.navurl(''MOD_ASS050'');'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_FUNCTION_RETURNING_URL'
        ,p_branch_when_button_id=>wwv_flow_api.id(8687872617618585)
        ,p_branch_sequence=>10
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(8719713152304144)
        ,p_branch_action=>'return app_navigation_pkg.navurl(''MOD_ASS050'');'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_FUNCTION_RETURNING_URL'
        ,p_branch_when_button_id=>wwv_flow_api.id(8687445897615342)
        ,p_branch_sequence=>30
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(8723686295729086)
        ,p_branch_action=>'begin'||wwv_flow.LF||
                          'return app_navigation_pkg.navurl;'||wwv_flow.LF||
                          'end;'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_FUNCTION_RETURNING_URL'
        ,p_branch_sequence=>40
        ,p_branch_condition_type=>'ALWAYS'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3128280294204010)
        ,p_name=>'P2_OFFENDER_PK'
        ,p_item_sequence=>30
        ,p_item_plug_id=>wwv_flow_api.id(8666613940647818)
        ,p_prompt=>'OFFENDER_PK'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(8676570395951234)
        ,p_name=>'P2_DATE_COMPLETED'
        ,p_item_sequence=>10
        ,p_item_plug_id=>wwv_flow_api.id(8666613940647818)
        ,p_prompt=>'New'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cHeight=>1
        ,p_label_alignment=>'RIGHT-CENTER'
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(8708742619711989)
        ,p_name=>'P2_OASYS_SET_PK'
        ,p_item_sequence=>20
        ,p_item_plug_id=>wwv_flow_api.id(8666613940647818)
        ,p_prompt=>'OASYS_SET_PK'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(9326756581974307)
        ,p_name=>'P2_PREVIOUS_MODULE'
        ,p_item_sequence=>40
        ,p_item_plug_id=>wwv_flow_api.id(8666613940647818)
        ,p_item_default=>'OFF030'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(8726701108844782)
        ,p_process_sequence=>10
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P2_MAKE_HISTORIC'
        ,p_process_sql_clob=>'assessment_utils_pkg.mark_assessment_historic(p_oasys_set_pk =>  :P2_OASYS_SET_PK );'
        ,p_error_display_location=>'INLINE_IN_NOTIFICATION'
        ,p_process_when_button_id=>wwv_flow_api.id(8687445897615342)
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(9261322565121083)
        ,p_process_sequence=>20
        ,p_process_point=>'BEFORE_HEADER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P2_OLBH'
        ,p_process_sql_clob=>'MOD_OFF030_PKG.P2_OLBH;'
        );
end;
/
prompt --application/pages/page_00010
begin
    wwv_flow_api.create_page(
            p_id=>10
        ,p_user_interface_id=>wwv_flow_api.id(74312104640072)
        ,p_name=>'Offender Record'
        ,p_alias=>'OFF030_LANDING'
        ,p_page_mode=>'NORMAL'
        ,p_step_title=>'Offender Record'
        ,p_step_sub_title=>'Offender Record'
        ,p_step_sub_title_type=>'TEXT_WITH_SUBSTITUTIONS'
        ,p_first_item=>'NO_FIRST_ITEM'
        ,p_autocomplete_on_off=>'OFF'
        ,p_group_id=>wwv_flow_api.id(6767457294623073)
        ,p_javascript_code=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'function LocationChanged(){',
            '  // submit page to redraw cascading LOVs',
            '  &AI_NAV_SYS.(''LOCATION_CHANGED'');',
            '}',
            '',
            'function setUnknownPNC()',
            '{',
            '  document.getElementById(''P10_PNC'').value = ''UNKNOWN PNC'';',
            '  $.safetynet.raiseChange(''#P10_PNC'');',
            '}',
            '',
            'function appdosubmitconfirm (pRequest) {',
            'var pnc = document.getElementById(''P10_PNC'').value;',
            'var mode = document.getElementById(''P10_MODE'').value;',
            '',
            '// if close button then return true',
            ' if ( pRequest.indexOf(''CLOSE'') != -1 ) {',
            '    return true;',
            ' }  ',
            '// if offender is being created then do not perform the PNC check.',
            ' if ( mode == ''CREATE'' ) {',
            '    return true;',
            ' }  ',
            ' var get = new htmldb_Get(null,$x(''pFlowId'').value,''APPLICATION_PROCESS=AP_OD_PNC_COUNT'',0);',
            ' get.add(''P10_PNC'', pnc);',
            ' ReturnValue = get.get();',
            ' get = null;',
            '',
            ' if(ReturnValue == 1) ',
            ' {',
            ' return (confirm("You have entered a PNC which already exists within the system, click OK to continue and trigger the merge process, click cancel to correct the PNC where applicable."))',
            ' }',
            '',
            ' if(ReturnValue == 2) ',
            ' {',
            ' return (alert("You have entered a PNC which already exists for an offender as part of an unresolved merge, therefore the PNC can not be entered until the merge has been resolved."))',
            ' }',
            '',
            ' if(ReturnValue == -1) ',
            ' {',
            ' return (alert("You are using a duplicate PNC and are unable to merge between two offenders in prison."))',
            ' }',
            '',
            ' if ( pRequest.indexOf(''DELETE_OFFENDER'') != -1 ) {',
            '    return (confirm("Please click OK to confirm the deletion."));',
            ' }  ',
            '',
            ' return true; ',
            ' }',
            '',
            ''))
        ,p_step_template=>wwv_flow_api.id(20789318053320561)
        ,p_page_template_options=>'#DEFAULT#'
        ,p_required_role=>wwv_flow_api.id(5052631587350853)
        ,p_dialog_chained=>'Y'
        ,p_overwrite_navigation_list=>'N'
        ,p_nav_list_template_options=>'#DEFAULT#'
        ,p_page_is_public_y_n=>'N'
        ,p_protection_level=>'C'
        ,p_cache_mode=>'NOCACHE'
        ,p_cache_timeout_seconds=>21600
        ,p_help_text=>'No help is available for this page.'
        ,p_last_updated_by=>'MIKEJACKSON'
        ,p_last_upd_yyyymmddhh24miss=>'20190611123314'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(2025110435637702)
        ,p_plug_name=>'New'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(20790622026320563)
        ,p_plug_display_sequence=>10
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_display_point=>'BODY'
        ,p_plug_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<script type="text/javascript">',
            'function x() {',
            'return document.getElementById(''P10_NEW_SENTENCE_PLAN_BASE_URL'').value + "/" + document.getElementById(''P10_OFFENDER_PK_1'').value;',
            '}',
            '',
            'var y = x();',
            '</script>',
            '<a href="javascript:window.open(y, ''_blank'')">New Sentence plan</a>'))
        ,p_plug_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(2772529639851778)
        ,p_plug_name=>'Offender Address'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(20790696787320563)
        ,p_plug_display_sequence=>4
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_plug_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_plug_column_width=>'valign=top'
        ,p_plug_display_condition_type=>'PLSQL_EXPRESSION'
        ,p_plug_display_when_condition=>':P10_TABSET1 = ''TABSET1_TAB2'' AND NVL(:P0_LAO_RESTRICTED,''N'') = ''N'''
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(2772921042851781)
        ,p_plug_name=>'Offender Management'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(3341723590971059)
        ,p_plug_display_sequence=>6
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_plug_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_plug_display_condition_type=>'PLSQL_EXPRESSION'
        ,p_plug_display_when_condition=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            ':P10_TABSET1 = ''TABSET1_TAB4'' ',
            'AND NVL(:P0_ACCESS_MODE,''N'') != ''BOILERPLATE'''))
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(2774115356851782)
        ,p_plug_name=>'hidden'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_display_sequence=>60
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BEFORE_FOOTER'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows=>15
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(2777502436851790)
        ,p_plug_name=>'Tabs1'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_display_sequence=>2
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  -- setup tabs',
            '  mod_OFF030_pkg.p10_setup_tabs;',
            '  ',
            '  app_ui_components_pkg.tabs_render(p_instance_id  => ''TABSET1'');',
            'EXCEPTION WHEN OTHERS',
            'THEN',
            '  ELOG_API.INS;',
            '  RAISE;',
            'end;',
            ''))
        ,p_plug_source_type=>'NATIVE_PLSQL'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(2805805081563614)
        ,p_plug_name=>'Offender'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(20790696787320563)
        ,p_plug_display_sequence=>1
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_report_region(
            p_id=>wwv_flow_api.id(2837929424403404)
        ,p_name=>'Alias'
        ,p_template=>wwv_flow_api.id(6452830739005671)
        ,p_display_sequence=>5
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_new_grid_row=>false
        ,p_display_column=>1
        ,p_display_point=>'BODY_3'
        ,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  return /*select 1 from dual */ mod_OFF030_pkg.off_alias_search_query; ',
            'end;',
            '',
            '',
            ' '))
        ,p_source_type=>'NATIVE_FNC_REPORT'
        ,p_display_when_condition=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '(:P10_TABSET1          = ''TABSET1_TAB3''',
            'OR :REQUEST           = ''P10_BT_LAO'' ',
            'OR ( :P10_LAO_ENABLED_FLAG = ''Enabled'' AND ',
            '     :P10_TABSET1          = ''TABSET1_TAB3''  ))'))
        ,p_display_condition_type=>'PLSQL_EXPRESSION'
        ,p_ajax_enabled=>'Y'
        ,p_fixed_header=>'NONE'
        ,p_query_row_template=>wwv_flow_api.id(15545672787664696)
        ,p_query_num_rows=>5
        ,p_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_query_show_nulls_as=>' - '
        ,p_query_break_cols=>'0'
        ,p_query_num_rows_type=>'SEARCH_ENGINE'
        ,p_query_row_count_max=>500
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_csv_output=>'N'
        ,p_sort_null=>'F'
        ,p_query_asc_image_attr=>'width="13" height="12" '
        ,p_query_desc_image_attr=>'width="13" height="12" '
        ,p_plug_query_strip_html=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(2838223248403414)
        ,p_query_column_id=>1
        ,p_column_alias=>'OFFENDER_ALIAS_PK'
        ,p_column_display_sequence=>1
        ,p_column_heading=>'Offender Alias Pk'
        ,p_heading_alignment=>'LEFT'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(2838300051403415)
        ,p_query_column_id=>2
        ,p_column_alias=>'ALIAS_DATE_OF_BIRTH'
        ,p_column_display_sequence=>5
        ,p_column_heading=>'Date Of Birth'
        ,p_column_link=>'#URL#'
        ,p_column_linktext=>'#ALIAS_DATE_OF_BIRTH#'
        ,p_heading_alignment=>'LEFT'
        ,p_display_as=>'WITHOUT_MODIFICATION'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(2838414618403415)
        ,p_query_column_id=>3
        ,p_column_alias=>'ALIAS_FAMILY_NAME'
        ,p_column_display_sequence=>2
        ,p_column_heading=>'Surname'
        ,p_heading_alignment=>'LEFT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(2838505611403415)
        ,p_query_column_id=>4
        ,p_column_alias=>'ALIAS_FORENAME_1'
        ,p_column_display_sequence=>3
        ,p_column_heading=>'Forename 1'
        ,p_heading_alignment=>'LEFT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(2838606712403415)
        ,p_query_column_id=>5
        ,p_column_alias=>'ALIAS_FORENAME_2'
        ,p_column_display_sequence=>4
        ,p_column_heading=>'Forename 2'
        ,p_heading_alignment=>'LEFT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(1628813645074148)
        ,p_query_column_id=>6
        ,p_column_alias=>'URL'
        ,p_column_display_sequence=>6
        ,p_column_heading=>'Url'
        ,p_hidden_column=>'Y'
        );
    wwv_flow_api.create_report_region(
            p_id=>wwv_flow_api.id(3157724148967235)
        ,p_name=>'OGP'
        ,p_template=>wwv_flow_api.id(20791510255320565)
        ,p_display_sequence=>7
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_new_grid_row=>false
        ,p_display_column=>1
        ,p_display_point=>'BODY_3'
        ,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '  RETURN /*select 1 from dual */ MOD_OFF030_PKG.ogp_report_query;',
            'END;'))
        ,p_source_type=>'NATIVE_FNC_REPORT'
        ,p_display_when_condition=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            ':P10_TABSET1 = ''TABSET1_TAB5'' AND NVL(:P0_LAO_RESTRICTED,''N'') = ''N''',
            'and mod_off030_pkg.p10_display_ogp_ovp_scores'))
        ,p_display_condition_type=>'PLSQL_EXPRESSION'
        ,p_header=>'           '
        ,p_footer=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<table class="reportregionfooter" style="width:90%">',
            '                ',
            '                    <tr>',
            '                        <td>',
            '                             <a>Section 3: Accommodation</a>',
            '                        </td>',
            '                        <td>',
            '                             <a>Section 8: Drug misuse</a>',
            '                        </td>',
            '                    </tr>',
            '',
            '                   <tr>',
            '                        <td>',
            '                             <a>Section 4: Employment</a>',
            '                        </td>',
            '                        <td>',
            '                             <a>Section 11: Thinking and behaviour</a>',
            '                        </td>',
            '            </tr>',
            '',
            '                   <tr>',
            '                        <td>',
            '                             <a>Section 7: Lifestyle and associates</a>',
            '                        </td>',
            '                        <td>',
            '                             <a>Section 12: Attitudes</a>',
            '                        </td>',
            '                    </tr>',
            '</table>'))
        ,p_ajax_enabled=>'Y'
        ,p_fixed_header=>'NONE'
        ,p_query_row_template=>wwv_flow_api.id(20794602676320568)
        ,p_query_num_rows=>15
        ,p_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_query_show_nulls_as=>' - '
        ,p_query_break_cols=>'0'
        ,p_query_num_rows_type=>'0'
        ,p_query_row_count_max=>500
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_csv_output=>'N'
        ,p_prn_output=>'N'
        ,p_prn_format=>'PDF'
        ,p_prn_output_show_link=>'Y'
        ,p_prn_output_link_text=>'Print'
        ,p_prn_content_disposition=>'ATTACHMENT'
        ,p_prn_document_header=>'APEX'
        ,p_prn_units=>'MILLIMETERS'
        ,p_prn_paper_size=>'A4'
        ,p_prn_width_units=>'PERCENTAGE'
        ,p_prn_width=>297
        ,p_prn_height=>210
        ,p_prn_orientation=>'HORIZONTAL'
        ,p_prn_page_header_font_color=>'#000000'
        ,p_prn_page_header_font_family=>'Helvetica'
        ,p_prn_page_header_font_weight=>'normal'
        ,p_prn_page_header_font_size=>'12'
        ,p_prn_page_footer_font_color=>'#000000'
        ,p_prn_page_footer_font_family=>'Helvetica'
        ,p_prn_page_footer_font_weight=>'normal'
        ,p_prn_page_footer_font_size=>'12'
        ,p_prn_header_bg_color=>'#9bafde'
        ,p_prn_header_font_color=>'#ffffff'
        ,p_prn_header_font_family=>'Helvetica'
        ,p_prn_header_font_weight=>'normal'
        ,p_prn_header_font_size=>'10'
        ,p_prn_body_bg_color=>'#efefef'
        ,p_prn_body_font_color=>'#000000'
        ,p_prn_body_font_family=>'Helvetica'
        ,p_prn_body_font_weight=>'normal'
        ,p_prn_body_font_size=>'10'
        ,p_prn_border_width=>.5
        ,p_prn_page_header_alignment=>'CENTER'
        ,p_prn_page_footer_alignment=>'CENTER'
        ,p_query_asc_image_attr=>'width="13" height="12" '
        ,p_query_desc_image_attr=>'width="13" height="12" '
        ,p_plug_query_strip_html=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3158010840967237)
        ,p_query_column_id=>1
        ,p_column_alias=>'OFFENDER_PK'
        ,p_column_display_sequence=>13
        ,p_column_heading=>'Offender Pk'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'N'
        ,p_print_col_width=>'0'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3158123430967237)
        ,p_query_column_id=>2
        ,p_column_alias=>'OASYS_ASSESSMENT_GROUP_PK'
        ,p_column_display_sequence=>14
        ,p_column_heading=>'Oasys Assessment Group Pk'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'N'
        ,p_print_col_width=>'0'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3158210311967237)
        ,p_query_column_id=>3
        ,p_column_alias=>'OASYS_SET_PK'
        ,p_column_display_sequence=>15
        ,p_column_heading=>'Oasys Set Pk'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'N'
        ,p_print_col_width=>'0'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3158316867967237)
        ,p_query_column_id=>4
        ,p_column_alias=>'DATE_COMPLETED'
        ,p_column_display_sequence=>1
        ,p_column_heading=>'Date Completed'
        ,p_heading_alignment=>'LEFT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'8'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3158421528967237)
        ,p_query_column_id=>5
        ,p_column_alias=>'OGP_1YEAR'
        ,p_column_display_sequence=>8
        ,p_column_heading=>'1 Yr%'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'8'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3158501671967237)
        ,p_query_column_id=>6
        ,p_column_alias=>'OGP_2YEAR'
        ,p_column_display_sequence=>9
        ,p_column_heading=>'2 Yr%'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'8'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3158606781967237)
        ,p_query_column_id=>7
        ,p_column_alias=>'OGP_RISK_RECON'
        ,p_column_display_sequence=>10
        ,p_column_heading=>'OGP Banding'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'8'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3158710710967237)
        ,p_query_column_id=>8
        ,p_column_alias=>'OGRS3_1YEAR'
        ,p_column_display_sequence=>11
        ,p_column_heading=>'OGRS3 1 Yr%'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'8'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3158817005967237)
        ,p_query_column_id=>9
        ,p_column_alias=>'OGRS3_2YEAR'
        ,p_column_display_sequence=>12
        ,p_column_heading=>'OGRS3 2 Yr%'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'8'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5775911730795245)
        ,p_query_column_id=>10
        ,p_column_alias=>'SECTION_3'
        ,p_column_display_sequence=>2
        ,p_column_heading=>'<span class="hidden4jaws">Section </span>3<span class="hidden4jaws">,</span>'
        ,p_use_as_row_header=>'N'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'8'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5776003679795246)
        ,p_query_column_id=>11
        ,p_column_alias=>'SECTION_4'
        ,p_column_display_sequence=>3
        ,p_column_heading=>'<span class="hidden4jaws">Section </span>4<span class="hidden4jaws">,</span>'
        ,p_use_as_row_header=>'N'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'8'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5776108482795246)
        ,p_query_column_id=>12
        ,p_column_alias=>'SECTION_7'
        ,p_column_display_sequence=>4
        ,p_column_heading=>'<span class="hidden4jaws">Section </span>7<span class="hidden4jaws">,</span>'
        ,p_use_as_row_header=>'N'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'8'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5776217078795246)
        ,p_query_column_id=>13
        ,p_column_alias=>'SECTION_8'
        ,p_column_display_sequence=>5
        ,p_column_heading=>'<span class="hidden4jaws">Section </span>8<span class="hidden4jaws">,</span>'
        ,p_use_as_row_header=>'N'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'8'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5776304663795246)
        ,p_query_column_id=>14
        ,p_column_alias=>'SECTION_11'
        ,p_column_display_sequence=>6
        ,p_column_heading=>'<span class="hidden4jaws">Section </span>11<span class="hidden4jaws">,</span>'
        ,p_use_as_row_header=>'N'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'8'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5776400995795246)
        ,p_query_column_id=>15
        ,p_column_alias=>'SECTION_12'
        ,p_column_display_sequence=>7
        ,p_column_heading=>'<span class="hidden4jaws">Section </span>12<span class="hidden4jaws">,</span>'
        ,p_use_as_row_header=>'N'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'8'
        );
    wwv_flow_api.create_report_region(
            p_id=>wwv_flow_api.id(3161504802990036)
        ,p_name=>'OVP'
        ,p_template=>wwv_flow_api.id(20791510255320565)
        ,p_display_sequence=>8
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_new_grid_row=>false
        ,p_display_column=>1
        ,p_display_point=>'BODY_3'
        ,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '  RETURN /*select 1 from dual */ MOD_OFF030_PKG.ovp_report_query;',
            'END;'))
        ,p_source_type=>'NATIVE_FNC_REPORT'
        ,p_display_when_condition=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            ':P10_TABSET1 = ''TABSET1_TAB5'' AND NVL(:P0_LAO_RESTRICTED,''N'') = ''N''',
            'and mod_off030_pkg.p10_display_ogp_ovp_scores'))
        ,p_display_condition_type=>'PLSQL_EXPRESSION'
        ,p_header=>'           '
        ,p_footer=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<table class="reportregionfooter" style="width:79%">',
            '                ',
            '                    <tr>',
            '                        <td>',
            '                           <a>Section 2: Recognises impact of offending?</a>',
            '                        </td>',
            '                        <td>',
            '                           <a>Section 10: Psychiatric Treatment</a>',
            '                        </td>',
            '                    </tr>',
            '                    <tr>',
            '                        <td>',
            '                           <a>Section 3: Accommodation</a>',
            '                        </td>',
            '                        <td>',
            '                           <a>Section 11: Temper control</a>',
            '                        </td>',
            '                    </tr>',
            '                    <tr>',
            '                        <td>',
            '                        <a>Section 4: Employment</a>',
            '                        </td>',
            '                        <td>',
            '                        <a>Section 12: Attitudes</a>',
            '                        </td>',
            '                    </tr>',
            '                    <tr>',
            '                        <td>',
            '                           <a>Section 9: Alcohol</a>',
            '                        </td>',
            '                    </tr>',
            '                </table>'))
        ,p_ajax_enabled=>'Y'
        ,p_fixed_header=>'NONE'
        ,p_query_row_template=>wwv_flow_api.id(20794602676320568)
        ,p_query_num_rows=>15
        ,p_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_query_show_nulls_as=>' - '
        ,p_query_break_cols=>'0'
        ,p_query_num_rows_type=>'0'
        ,p_query_row_count_max=>500
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_csv_output=>'N'
        ,p_prn_output=>'N'
        ,p_sort_null=>'F'
        ,p_query_asc_image_attr=>'width="13" height="12" '
        ,p_query_desc_image_attr=>'width="13" height="12" '
        ,p_plug_query_strip_html=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3161827206990037)
        ,p_query_column_id=>1
        ,p_column_alias=>'OFFENDER_PK'
        ,p_column_display_sequence=>1
        ,p_column_heading=>'Offender Pk'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3161907289990037)
        ,p_query_column_id=>2
        ,p_column_alias=>'OASYS_SET_PK'
        ,p_column_display_sequence=>2
        ,p_column_heading=>'Oasys Set Pk'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3162015982990038)
        ,p_query_column_id=>3
        ,p_column_alias=>'DATE_COMPLETED'
        ,p_column_display_sequence=>3
        ,p_column_heading=>'Date Completed'
        ,p_heading_alignment=>'LEFT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3162101038990038)
        ,p_query_column_id=>4
        ,p_column_alias=>'OVP_1YEAR'
        ,p_column_display_sequence=>11
        ,p_column_heading=>'1 Yr%'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3162202433990038)
        ,p_query_column_id=>5
        ,p_column_alias=>'OVP_2YEAR'
        ,p_column_display_sequence=>12
        ,p_column_heading=>'2 Yr%'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3162331345990038)
        ,p_query_column_id=>6
        ,p_column_alias=>'OVP_RISK_RECON'
        ,p_column_display_sequence=>13
        ,p_column_heading=>'OVP Banding'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5776518656797265)
        ,p_query_column_id=>7
        ,p_column_alias=>'SECTION_2'
        ,p_column_display_sequence=>4
        ,p_column_heading=>'<span class="hidden4jaws">Section </span>2<span class="hidden4jaws">,</span>'
        ,p_use_as_row_header=>'N'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5776632134797265)
        ,p_query_column_id=>8
        ,p_column_alias=>'SECTION_3'
        ,p_column_display_sequence=>5
        ,p_column_heading=>'<span class="hidden4jaws">Section </span>3<span class="hidden4jaws">,</span>'
        ,p_use_as_row_header=>'N'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5776728911797265)
        ,p_query_column_id=>9
        ,p_column_alias=>'SECTION_4'
        ,p_column_display_sequence=>6
        ,p_column_heading=>'<span class="hidden4jaws">Section </span>4<span class="hidden4jaws">,</span>'
        ,p_use_as_row_header=>'N'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5776815851797265)
        ,p_query_column_id=>10
        ,p_column_alias=>'SECTION_9'
        ,p_column_display_sequence=>7
        ,p_column_heading=>'<span class="hidden4jaws">Section </span>9<span class="hidden4jaws">,</span>'
        ,p_use_as_row_header=>'N'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5776919371797265)
        ,p_query_column_id=>11
        ,p_column_alias=>'SECTION_10'
        ,p_column_display_sequence=>8
        ,p_column_heading=>'<span class="hidden4jaws">Section </span>10<span class="hidden4jaws">,</span>'
        ,p_use_as_row_header=>'N'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5777008518797265)
        ,p_query_column_id=>12
        ,p_column_alias=>'SECTION_11'
        ,p_column_display_sequence=>9
        ,p_column_heading=>'<span class="hidden4jaws">Section </span>11<span class="hidden4jaws">,</span>'
        ,p_use_as_row_header=>'N'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5777122352797265)
        ,p_query_column_id=>13
        ,p_column_alias=>'SECTION_12'
        ,p_column_display_sequence=>10
        ,p_column_heading=>'<span class="hidden4jaws">Section </span>12<span class="hidden4jaws">,</span>'
        ,p_use_as_row_header=>'N'
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'RIGHT'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_region(
            p_id=>wwv_flow_api.id(3184125196393294)
        ,p_name=>'Offender History'
        ,p_template=>wwv_flow_api.id(20791510255320565)
        ,p_display_sequence=>9
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_new_grid_row=>false
        ,p_display_column=>1
        ,p_display_point=>'BODY_3'
        ,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '  RETURN /*select 1 from dual */ MOD_OFF030_PKG.off_history_report_query;',
            'END;'))
        ,p_source_type=>'NATIVE_FNC_REPORT'
        ,p_display_when_condition=>':P10_TABSET1 = ''TABSET1_TAB6'' AND NVL(:P0_LAO_RESTRICTED,''N'') = ''N'''
        ,p_display_condition_type=>'PLSQL_EXPRESSION'
        ,p_ajax_enabled=>'Y'
        ,p_fixed_header=>'NONE'
        ,p_query_row_template=>wwv_flow_api.id(20794602676320568)
        ,p_query_num_rows=>15
        ,p_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_query_show_nulls_as=>' - '
        ,p_query_break_cols=>'0'
        ,p_query_num_rows_type=>'SEARCH_ENGINE'
        ,p_query_row_count_max=>500
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_break_type_flag=>'DEFAULT_BREAK_FORMATTING'
        ,p_csv_output=>'N'
        ,p_prn_output=>'N'
        ,p_sort_null=>'F'
        ,p_query_asc_image_attr=>'width="13" height="12" '
        ,p_query_desc_image_attr=>'width="13" height="12" '
        ,p_plug_query_strip_html=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3184409416393296)
        ,p_query_column_id=>1
        ,p_column_alias=>'OFFENDER_HISTORY_PK'
        ,p_column_display_sequence=>9
        ,p_column_heading=>'Offender History Pk'
        ,p_heading_alignment=>'LEFT'
        ,p_default_sort_column_sequence=>1
        ,p_disable_sort_column=>'N'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3184527614393296)
        ,p_query_column_id=>2
        ,p_column_alias=>'START_DATE'
        ,p_column_display_sequence=>1
        ,p_column_heading=>'Start Date'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3184601182393296)
        ,p_query_column_id=>3
        ,p_column_alias=>'OFFENDER_PK'
        ,p_column_display_sequence=>10
        ,p_column_heading=>'Offender Pk'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3184813764393296)
        ,p_query_column_id=>4
        ,p_column_alias=>'PRISON_EST'
        ,p_column_display_sequence=>2
        ,p_column_heading=>'Prison Establishment'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3184918147393296)
        ,p_query_column_id=>5
        ,p_column_alias=>'OFFENDER_SUPERVISOR'
        ,p_column_display_sequence=>3
        ,p_column_heading=>'Offender Supervisor'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3185012498393296)
        ,p_query_column_id=>6
        ,p_column_alias=>'PROBATION_AREA'
        ,p_column_display_sequence=>4
        ,p_column_heading=>'Probation Provider'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3185108405393296)
        ,p_query_column_id=>7
        ,p_column_alias=>'PROBATION_TEAM'
        ,p_column_display_sequence=>5
        ,p_column_heading=>'Probation Team'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3185229237393296)
        ,p_query_column_id=>8
        ,p_column_alias=>'OFFENDER_MANAGER'
        ,p_column_display_sequence=>6
        ,p_column_heading=>'Offender Manager'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3185324220393296)
        ,p_query_column_id=>9
        ,p_column_alias=>'CONTROLING_OWNER'
        ,p_column_display_sequence=>7
        ,p_column_heading=>'Controlling Owner'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(3185415439393296)
        ,p_query_column_id=>10
        ,p_column_alias=>'PHYSICAL_LOCATION'
        ,p_column_display_sequence=>8
        ,p_column_heading=>'Physical Location'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_region(
            p_id=>wwv_flow_api.id(3205403556774978)
        ,p_name=>'Merge History'
        ,p_template=>wwv_flow_api.id(20791510255320565)
        ,p_display_sequence=>10
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_new_grid_row=>false
        ,p_display_column=>1
        ,p_display_point=>'BODY_3'
        ,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '  RETURN /*select 1 from dual */ MOD_OFF030_PKG.merge_history_report_query;',
            'END;'))
        ,p_source_type=>'NATIVE_FNC_REPORT'
        ,p_display_when_condition=>'P10_TABSET1'
        ,p_display_when_cond2=>'TABSET1_TAB6'
        ,p_display_condition_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_ajax_enabled=>'Y'
        ,p_fixed_header=>'NONE'
        ,p_query_row_template=>wwv_flow_api.id(20794602676320568)
        ,p_query_num_rows=>15
        ,p_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_query_show_nulls_as=>' - '
        ,p_query_break_cols=>'0'
        ,p_query_num_rows_type=>'SEARCH_ENGINE'
        ,p_query_row_count_max=>500
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_break_type_flag=>'DEFAULT_BREAK_FORMATTING'
        ,p_csv_output=>'N'
        ,p_prn_output=>'N'
        ,p_query_asc_image_attr=>'width="13" height="12" '
        ,p_query_desc_image_attr=>'width="13" height="12" '
        ,p_plug_query_strip_html=>'Y'
        );
end;
/
begin
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(8724308574425694)
        ,p_query_column_id=>1
        ,p_column_alias=>'MERGED_DATE'
        ,p_column_display_sequence=>1
        ,p_column_heading=>'Date of Merge'
        ,p_heading_alignment=>'LEFT'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(8724408920425694)
        ,p_query_column_id=>2
        ,p_column_alias=>'LOSER_PROB_AREA_DESC'
        ,p_column_display_sequence=>2
        ,p_column_heading=>'Previous Owning Probation Provider'
        ,p_heading_alignment=>'LEFT'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(8724532689425694)
        ,p_query_column_id=>3
        ,p_column_alias=>'LOSER_PRISON_AREA_DESC'
        ,p_column_display_sequence=>3
        ,p_column_heading=>'Previous Owning Prison Establishment'
        ,p_heading_alignment=>'LEFT'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(3212125747008462)
        ,p_plug_name=>'Additional Provider'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(3341723590971059)
        ,p_plug_display_sequence=>13
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows=>15
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_plug_display_condition_type=>'PLSQL_EXPRESSION'
        ,p_plug_display_when_condition=>':P10_TABSET1 = ''TABSET1_TAB4'' AND NVL(:P0_LAO_RESTRICTED,''N'') = ''N'''
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(3212303714011632)
        ,p_plug_name=>'Owner Details'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(20790696787320563)
        ,p_plug_display_sequence=>12
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows=>15
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_plug_display_condition_type=>'PLSQL_EXPRESSION'
        ,p_plug_display_when_condition=>':P10_TABSET1 = ''TABSET1_TAB4'' /* AND NVL(:P10_LIMITED_ACCESS_OFFENDER,''N'') = ''N'' */'
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_report_region(
            p_id=>wwv_flow_api.id(5431525104908933)
        ,p_name=>'Auditable Events'
        ,p_template=>wwv_flow_api.id(20791510255320565)
        ,p_display_sequence=>11
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_new_grid_row=>false
        ,p_display_column=>1
        ,p_display_point=>'BODY_3'
        ,p_source=>'return mod_off030_pkg.auditable_events_report_query;'
        ,p_source_type=>'NATIVE_FNC_REPORT'
        ,p_display_when_condition=>'P10_TABSET1'
        ,p_display_when_cond2=>'TABSET1_TAB6'
        ,p_display_condition_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_ajax_enabled=>'Y'
        ,p_fixed_header=>'NONE'
        ,p_query_row_template=>wwv_flow_api.id(20794602676320568)
        ,p_plug_query_max_columns=>60
        ,p_query_num_rows=>15
        ,p_query_options=>'GENERIC_REPORT_COLUMNS'
        ,p_query_show_nulls_as=>' - '
        ,p_query_break_cols=>'0'
        ,p_query_num_rows_type=>'SEARCH_ENGINE'
        ,p_query_row_count_max=>500
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_csv_output=>'N'
        ,p_prn_output=>'N'
        ,p_query_asc_image_attr=>'width="13" height="12" '
        ,p_query_desc_image_attr=>'width="13" height="12" '
        ,p_plug_query_strip_html=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5434217185934990)
        ,p_query_column_id=>1
        ,p_column_alias=>'COL01'
        ,p_column_display_sequence=>1
        ,p_column_heading=>'<span class="hidden4jaws">Auditable Events </span>Event Name'
        ,p_use_as_row_header=>'N'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5434332004934990)
        ,p_query_column_id=>2
        ,p_column_alias=>'COL02'
        ,p_column_display_sequence=>2
        ,p_column_heading=>'<span class="hidden4jaws">Auditable Events </span>Date/Time'
        ,p_use_as_row_header=>'N'
        ,p_column_format=>'DD/MM/YYYY HH24:MI'
        ,p_heading_alignment=>'LEFT'
        ,p_default_sort_column_sequence=>1
        ,p_default_sort_dir=>'desc'
        ,p_disable_sort_column=>'N'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5434429201934991)
        ,p_query_column_id=>3
        ,p_column_alias=>'COL03'
        ,p_column_display_sequence=>3
        ,p_column_heading=>'<span class="hidden4jaws">Auditable Events </span>Username'
        ,p_use_as_row_header=>'N'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5434509594934991)
        ,p_query_column_id=>4
        ,p_column_alias=>'COL04'
        ,p_column_display_sequence=>4
        ,p_column_heading=>'<span class="hidden4jaws">Auditable Events </span>LAO Status'
        ,p_use_as_row_header=>'N'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5434632200934991)
        ,p_query_column_id=>5
        ,p_column_alias=>'COL05'
        ,p_column_display_sequence=>5
        ,p_column_heading=>'<span class="hidden4jaws">Auditable Events </span>Details'
        ,p_use_as_row_header=>'N'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5434700237934991)
        ,p_query_column_id=>6
        ,p_column_alias=>'COL06'
        ,p_column_display_sequence=>6
        ,p_column_heading=>'<span class="hidden4jaws">Auditable Events </span>User IP Address'
        ,p_use_as_row_header=>'N'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        ,p_derived_column=>'N'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5434811376934991)
        ,p_query_column_id=>7
        ,p_column_alias=>'COL07'
        ,p_column_display_sequence=>7
        ,p_column_heading=>'Col07'
        ,p_heading_alignment=>'LEFT'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5434918808934991)
        ,p_query_column_id=>8
        ,p_column_alias=>'COL08'
        ,p_column_display_sequence=>8
        ,p_column_heading=>'Col08'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5435032079934991)
        ,p_query_column_id=>9
        ,p_column_alias=>'COL09'
        ,p_column_display_sequence=>9
        ,p_column_heading=>'Col09'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5435118178934991)
        ,p_query_column_id=>10
        ,p_column_alias=>'COL10'
        ,p_column_display_sequence=>10
        ,p_column_heading=>'Col10'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5435204129934991)
        ,p_query_column_id=>11
        ,p_column_alias=>'COL11'
        ,p_column_display_sequence=>11
        ,p_column_heading=>'Col11'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5435309549934991)
        ,p_query_column_id=>12
        ,p_column_alias=>'COL12'
        ,p_column_display_sequence=>12
        ,p_column_heading=>'Col12'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5435413246934991)
        ,p_query_column_id=>13
        ,p_column_alias=>'COL13'
        ,p_column_display_sequence=>13
        ,p_column_heading=>'Col13'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5435510788934991)
        ,p_query_column_id=>14
        ,p_column_alias=>'COL14'
        ,p_column_display_sequence=>14
        ,p_column_heading=>'Col14'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5435612478934991)
        ,p_query_column_id=>15
        ,p_column_alias=>'COL15'
        ,p_column_display_sequence=>15
        ,p_column_heading=>'Col15'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5435709662934992)
        ,p_query_column_id=>16
        ,p_column_alias=>'COL16'
        ,p_column_display_sequence=>16
        ,p_column_heading=>'Col16'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5435818196934992)
        ,p_query_column_id=>17
        ,p_column_alias=>'COL17'
        ,p_column_display_sequence=>17
        ,p_column_heading=>'Col17'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5435920141934992)
        ,p_query_column_id=>18
        ,p_column_alias=>'COL18'
        ,p_column_display_sequence=>18
        ,p_column_heading=>'Col18'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5436026084934992)
        ,p_query_column_id=>19
        ,p_column_alias=>'COL19'
        ,p_column_display_sequence=>19
        ,p_column_heading=>'Col19'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5436106130934992)
        ,p_query_column_id=>20
        ,p_column_alias=>'COL20'
        ,p_column_display_sequence=>20
        ,p_column_heading=>'Col20'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5436229426934992)
        ,p_query_column_id=>21
        ,p_column_alias=>'COL21'
        ,p_column_display_sequence=>21
        ,p_column_heading=>'Col21'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5436317167934992)
        ,p_query_column_id=>22
        ,p_column_alias=>'COL22'
        ,p_column_display_sequence=>22
        ,p_column_heading=>'Col22'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5436404994934992)
        ,p_query_column_id=>23
        ,p_column_alias=>'COL23'
        ,p_column_display_sequence=>23
        ,p_column_heading=>'Col23'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5436518733934992)
        ,p_query_column_id=>24
        ,p_column_alias=>'COL24'
        ,p_column_display_sequence=>24
        ,p_column_heading=>'Col24'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5436625222934992)
        ,p_query_column_id=>25
        ,p_column_alias=>'COL25'
        ,p_column_display_sequence=>25
        ,p_column_heading=>'Col25'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5436701455934992)
        ,p_query_column_id=>26
        ,p_column_alias=>'COL26'
        ,p_column_display_sequence=>26
        ,p_column_heading=>'Col26'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5436826723934992)
        ,p_query_column_id=>27
        ,p_column_alias=>'COL27'
        ,p_column_display_sequence=>27
        ,p_column_heading=>'Col27'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5436908653934992)
        ,p_query_column_id=>28
        ,p_column_alias=>'COL28'
        ,p_column_display_sequence=>28
        ,p_column_heading=>'Col28'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5437003771934992)
        ,p_query_column_id=>29
        ,p_column_alias=>'COL29'
        ,p_column_display_sequence=>29
        ,p_column_heading=>'Col29'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5437127652934992)
        ,p_query_column_id=>30
        ,p_column_alias=>'COL30'
        ,p_column_display_sequence=>30
        ,p_column_heading=>'Col30'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5437216122934992)
        ,p_query_column_id=>31
        ,p_column_alias=>'COL31'
        ,p_column_display_sequence=>31
        ,p_column_heading=>'Col31'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5437322405934992)
        ,p_query_column_id=>32
        ,p_column_alias=>'COL32'
        ,p_column_display_sequence=>32
        ,p_column_heading=>'Col32'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5437427807934992)
        ,p_query_column_id=>33
        ,p_column_alias=>'COL33'
        ,p_column_display_sequence=>33
        ,p_column_heading=>'Col33'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5437509963934992)
        ,p_query_column_id=>34
        ,p_column_alias=>'COL34'
        ,p_column_display_sequence=>34
        ,p_column_heading=>'Col34'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5437603182934993)
        ,p_query_column_id=>35
        ,p_column_alias=>'COL35'
        ,p_column_display_sequence=>35
        ,p_column_heading=>'Col35'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5437704825934993)
        ,p_query_column_id=>36
        ,p_column_alias=>'COL36'
        ,p_column_display_sequence=>36
        ,p_column_heading=>'Col36'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5437830740934993)
        ,p_query_column_id=>37
        ,p_column_alias=>'COL37'
        ,p_column_display_sequence=>37
        ,p_column_heading=>'Col37'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5437924654934993)
        ,p_query_column_id=>38
        ,p_column_alias=>'COL38'
        ,p_column_display_sequence=>38
        ,p_column_heading=>'Col38'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5438002534934993)
        ,p_query_column_id=>39
        ,p_column_alias=>'COL39'
        ,p_column_display_sequence=>39
        ,p_column_heading=>'Col39'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5438109178934993)
        ,p_query_column_id=>40
        ,p_column_alias=>'COL40'
        ,p_column_display_sequence=>40
        ,p_column_heading=>'Col40'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5438226299934993)
        ,p_query_column_id=>41
        ,p_column_alias=>'COL41'
        ,p_column_display_sequence=>41
        ,p_column_heading=>'Col41'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5438311766934993)
        ,p_query_column_id=>42
        ,p_column_alias=>'COL42'
        ,p_column_display_sequence=>42
        ,p_column_heading=>'Col42'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5438401046934994)
        ,p_query_column_id=>43
        ,p_column_alias=>'COL43'
        ,p_column_display_sequence=>43
        ,p_column_heading=>'Col43'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5438508740934994)
        ,p_query_column_id=>44
        ,p_column_alias=>'COL44'
        ,p_column_display_sequence=>44
        ,p_column_heading=>'Col44'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5438603869934994)
        ,p_query_column_id=>45
        ,p_column_alias=>'COL45'
        ,p_column_display_sequence=>45
        ,p_column_heading=>'Col45'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5438721933934994)
        ,p_query_column_id=>46
        ,p_column_alias=>'COL46'
        ,p_column_display_sequence=>46
        ,p_column_heading=>'Col46'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5438832010934994)
        ,p_query_column_id=>47
        ,p_column_alias=>'COL47'
        ,p_column_display_sequence=>47
        ,p_column_heading=>'Col47'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5438905141934994)
        ,p_query_column_id=>48
        ,p_column_alias=>'COL48'
        ,p_column_display_sequence=>48
        ,p_column_heading=>'Col48'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5439020508934994)
        ,p_query_column_id=>49
        ,p_column_alias=>'COL49'
        ,p_column_display_sequence=>49
        ,p_column_heading=>'Col49'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5439107832934994)
        ,p_query_column_id=>50
        ,p_column_alias=>'COL50'
        ,p_column_display_sequence=>50
        ,p_column_heading=>'Col50'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5439203122934994)
        ,p_query_column_id=>51
        ,p_column_alias=>'COL51'
        ,p_column_display_sequence=>51
        ,p_column_heading=>'Col51'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5439331055934994)
        ,p_query_column_id=>52
        ,p_column_alias=>'COL52'
        ,p_column_display_sequence=>52
        ,p_column_heading=>'Col52'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5439401177934994)
        ,p_query_column_id=>53
        ,p_column_alias=>'COL53'
        ,p_column_display_sequence=>53
        ,p_column_heading=>'Col53'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5439511219934994)
        ,p_query_column_id=>54
        ,p_column_alias=>'COL54'
        ,p_column_display_sequence=>54
        ,p_column_heading=>'Col54'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5439610415934994)
        ,p_query_column_id=>55
        ,p_column_alias=>'COL55'
        ,p_column_display_sequence=>55
        ,p_column_heading=>'Col55'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5439721616934994)
        ,p_query_column_id=>56
        ,p_column_alias=>'COL56'
        ,p_column_display_sequence=>56
        ,p_column_heading=>'Col56'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5439820327934994)
        ,p_query_column_id=>57
        ,p_column_alias=>'COL57'
        ,p_column_display_sequence=>57
        ,p_column_heading=>'Col57'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5439907245934994)
        ,p_query_column_id=>58
        ,p_column_alias=>'COL58'
        ,p_column_display_sequence=>58
        ,p_column_heading=>'Col58'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5440018909934994)
        ,p_query_column_id=>59
        ,p_column_alias=>'COL59'
        ,p_column_display_sequence=>59
        ,p_column_heading=>'Col59'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5440104113934994)
        ,p_query_column_id=>60
        ,p_column_alias=>'COL60'
        ,p_column_display_sequence=>60
        ,p_column_heading=>'Col60'
        );
    wwv_flow_api.create_report_region(
            p_id=>wwv_flow_api.id(5522906992105444)
        ,p_name=>'Assessments Report'
        ,p_template=>wwv_flow_api.id(6452830739005671)
        ,p_display_sequence=>3
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_new_grid_row=>false
        ,p_display_column=>1
        ,p_display_point=>'BODY_3'
        ,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '   return /* SELECT 1 from DUAL */ mod_OFF030_pkg.p10_display_off_assessments;',
            'END;'))
        ,p_source_type=>'NATIVE_FNC_REPORT'
        ,p_display_when_condition=>':P10_TABSET1 = ''TABSET1_TAB1'' AND NVL(:P0_LAO_RESTRICTED,''N'') = ''N'''
        ,p_display_condition_type=>'PLSQL_EXPRESSION'
        ,p_ajax_enabled=>'Y'
        ,p_fixed_header=>'NONE'
        ,p_query_row_template=>wwv_flow_api.id(15545672787664696)
        ,p_query_num_rows=>15
        ,p_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_query_break_cols=>'0'
        ,p_query_num_rows_type=>'SEARCH_ENGINE'
        ,p_query_row_count_max=>500
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_break_type_flag=>'DEFAULT_BREAK_FORMATTING'
        ,p_csv_output=>'N'
        ,p_prn_output=>'N'
        ,p_sort_null=>'F'
        ,p_query_asc_image_attr=>'width="13" height="12" '
        ,p_query_desc_image_attr=>'width="13" height="12" '
        ,p_plug_query_strip_html=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5556130606642096)
        ,p_query_column_id=>1
        ,p_column_alias=>'PK'
        ,p_column_display_sequence=>1
        ,p_hidden_column=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5556221599642096)
        ,p_query_column_id=>2
        ,p_column_alias=>'ASSESSMENT_STATUS_ELM'
        ,p_column_display_sequence=>2
        ,p_column_heading=>'Status'
        ,p_heading_alignment=>'LEFT'
        ,p_display_as=>'WITHOUT_MODIFICATION'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5556308952642097)
        ,p_query_column_id=>3
        ,p_column_alias=>'STATUS'
        ,p_column_display_sequence=>4
        ,p_hidden_column=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5556415827642097)
        ,p_query_column_id=>4
        ,p_column_alias=>'ASMTYPE'
        ,p_column_display_sequence=>6
        ,p_hidden_column=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5556530491642097)
        ,p_query_column_id=>5
        ,p_column_alias=>'PURPOSE'
        ,p_column_display_sequence=>5
        ,p_column_heading=>'Purpose of Assessment'
        ,p_heading_alignment=>'LEFT'
        ,p_display_as=>'WITHOUT_MODIFICATION'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5556610334642097)
        ,p_query_column_id=>6
        ,p_column_alias=>'DATE_COMPLETE'
        ,p_column_display_sequence=>3
        ,p_column_heading=>'Date Completed'
        ,p_heading_alignment=>'LEFT'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5556821945642097)
        ,p_query_column_id=>7
        ,p_column_alias=>'LOCKING_BUTTON'
        ,p_column_display_sequence=>7
        ,p_column_heading=>'Lock Assessment'
        ,p_heading_alignment=>'LEFT'
        ,p_display_as=>'WITHOUT_MODIFICATION'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5556931168642097)
        ,p_query_column_id=>8
        ,p_column_alias=>'CREATE_DATE'
        ,p_column_display_sequence=>8
        ,p_hidden_column=>'Y'
        );
    wwv_flow_api.create_report_region(
            p_id=>wwv_flow_api.id(6455713139036023)
        ,p_name=>'RFI'
        ,p_template=>wwv_flow_api.id(6452830739005671)
        ,p_display_sequence=>15
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_new_grid_row=>false
        ,p_display_column=>1
        ,p_display_point=>'BODY_3'
        ,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '  RETURN /* select * from dual */ mod_off030_pkg.rfi_report_query;',
            'END;',
            ''))
        ,p_source_type=>'NATIVE_FNC_REPORT'
        ,p_required_role=>wwv_flow_api.id(7169120974722037)
        ,p_display_when_condition=>'RETURN (:P10_TABSET1 = ''TABSET1_TAB7'');'
        ,p_display_condition_type=>'FUNCTION_BODY'
        ,p_ajax_enabled=>'Y'
        ,p_fixed_header=>'NONE'
        ,p_query_row_template=>wwv_flow_api.id(15545672787664696)
        ,p_query_num_rows=>15
        ,p_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_query_show_nulls_as=>' - '
        ,p_query_break_cols=>'0'
        ,p_query_num_rows_type=>'SEARCH_ENGINE'
        ,p_query_row_count_max=>500
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_csv_output=>'N'
        ,p_prn_output=>'N'
        ,p_query_asc_image_attr=>'width="13" height="12" '
        ,p_query_desc_image_attr=>'width="13" height="12" '
        ,p_plug_query_strip_html=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5791309164409505)
        ,p_query_column_id=>1
        ,p_column_alias=>'REQUESTOR_OASYS_USER_CODE_DESC'
        ,p_column_display_sequence=>1
        ,p_column_heading=>'Requestor Name'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5791422366409505)
        ,p_query_column_id=>2
        ,p_column_alias=>'DATE_REQUEST_MADE'
        ,p_column_display_sequence=>2
        ,p_column_heading=>'Date Request Made'
        ,p_heading_alignment=>'LEFT'
        ,p_default_sort_column_sequence=>1
        ,p_default_sort_dir=>'desc'
        ,p_disable_sort_column=>'N'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5791521427409505)
        ,p_query_column_id=>3
        ,p_column_alias=>'RESPONDER_OASYS_USER_CODE_DESC'
        ,p_column_display_sequence=>3
        ,p_column_heading=>'Internal Recipient'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5791603887409505)
        ,p_query_column_id=>4
        ,p_column_alias=>'RESPONDER_TEAM_CODE_DESC'
        ,p_column_display_sequence=>4
        ,p_column_heading=>'Team'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5791729765409505)
        ,p_query_column_id=>5
        ,p_column_alias=>'RESPONDER_DIVISION_CODE_DESC'
        ,p_column_display_sequence=>5
        ,p_column_heading=>'LDU'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5791803190409505)
        ,p_query_column_id=>6
        ,p_column_alias=>'RESPONDER_EXTERNAL_USER_DESC'
        ,p_column_display_sequence=>6
        ,p_column_heading=>'External Recipient'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5791919019409506)
        ,p_query_column_id=>7
        ,p_column_alias=>'RFI_TYPE_ELM_DESC'
        ,p_column_display_sequence=>7
        ,p_column_heading=>'Source'
        ,p_heading_alignment=>'LEFT'
        ,p_hidden_column=>'Y'
        ,p_display_as=>'WITHOUT_MODIFICATION'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5792015490409506)
        ,p_query_column_id=>8
        ,p_column_alias=>'PURPOSE_ASSESSMENT_ELM_DESC'
        ,p_column_display_sequence=>9
        ,p_column_heading=>'Reason for Request'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5792117404409506)
        ,p_query_column_id=>9
        ,p_column_alias=>'RESPONSE_STATUS_ELM_DESC'
        ,p_column_display_sequence=>10
        ,p_column_heading=>'Status'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5792201318409506)
        ,p_query_column_id=>10
        ,p_column_alias=>'NAV_URL'
        ,p_column_display_sequence=>8
        ,p_column_heading=>'Source'
        ,p_heading_alignment=>'LEFT'
        ,p_disable_sort_column=>'N'
        ,p_display_as=>'WITHOUT_MODIFICATION'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(6630020201991833)
        ,p_plug_name=>'Tab Border '
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(6452830739005671)
        ,p_plug_display_sequence=>18
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_plug_display_condition_type=>'PLSQL_EXPRESSION'
        ,p_plug_display_when_condition=>'NVL(:P10_LIMITED_ACCESS_OFFENDER,''N'') = ''Y'' AND :P10_TABSET1 IN (''TABSET1_TAB1'',''TABSET1_TAB2'');'
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_report_region(
            p_id=>wwv_flow_api.id(10826303560893536)
        ,p_name=>'INTBDTTO'
        ,p_template=>wwv_flow_api.id(6452830739005671)
        ,p_display_sequence=>17
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_new_grid_row=>false
        ,p_display_column=>1
        ,p_display_point=>'BODY_3'
        ,p_source=>'return /*select 1 from dual */ mod_OFF030_pkg.p10_intbdtto_report_query;'
        ,p_source_type=>'NATIVE_FNC_REPORT'
        ,p_display_when_condition=>'P10_TABSET1'
        ,p_display_when_cond2=>'TABSET1_TAB8'
        ,p_display_condition_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_header=>'You should not disclose this information to the offender or the offender''s representative unless you are sure that it is appropriate to do so. Providing this could, in some cases, put others at risk (e.g. the victim).'
        ,p_ajax_enabled=>'Y'
        ,p_fixed_header=>'NONE'
        ,p_query_row_template=>wwv_flow_api.id(15545672787664696)
        ,p_query_num_rows=>15
        ,p_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_query_show_nulls_as=>' - '
        ,p_query_break_cols=>'0'
        ,p_query_num_rows_type=>'SEARCH_ENGINE'
        ,p_query_row_count_max=>500
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_csv_output=>'N'
        ,p_prn_output=>'N'
        ,p_sort_null=>'F'
        ,p_query_asc_image_attr=>'width="13" height="12" '
        ,p_query_desc_image_attr=>'width="13" height="12" '
        ,p_plug_query_strip_html=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(12908927733633169)
        ,p_query_column_id=>1
        ,p_column_alias=>'STATUS'
        ,p_column_display_sequence=>1
        ,p_column_heading=>'Status'
        ,p_heading_alignment=>'LEFT'
        ,p_display_as=>'WITHOUT_MODIFICATION'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(12909022270633169)
        ,p_query_column_id=>2
        ,p_column_alias=>'NOT_DISCD_SECT_COMP_DATE'
        ,p_column_display_sequence=>2
        ,p_column_heading=>'Date'
        ,p_heading_alignment=>'LEFT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(12909110174633169)
        ,p_query_column_id=>3
        ,p_column_alias=>'NAME'
        ,p_column_display_sequence=>3
        ,p_column_heading=>'Name'
        ,p_heading_alignment=>'LEFT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(12909201231633169)
        ,p_query_column_id=>4
        ,p_column_alias=>'NOTES'
        ,p_column_display_sequence=>4
        ,p_column_heading=>'Notes'
        ,p_heading_alignment=>'LEFT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(12909303570633169)
        ,p_query_column_id=>5
        ,p_column_alias=>'NAV_URL'
        ,p_column_display_sequence=>5
        ,p_column_heading=>'Nav Url'
        ,p_hidden_column=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(12909428435633169)
        ,p_query_column_id=>6
        ,p_column_alias=>'NAV_REQ'
        ,p_column_display_sequence=>6
        ,p_column_heading=>'Nav Req'
        ,p_hidden_column=>'Y'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(11436929263517810)
        ,p_plug_name=>'RFI NOTE'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(20790622026320563)
        ,p_plug_display_sequence=>16
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_plug_display_condition_type=>'FUNCTION_BODY'
        ,p_plug_display_when_condition=>'RETURN (:P10_TABSET1 = ''TABSET1_TAB7'');'
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(12253521640691554)
        ,p_plug_name=>'Data Retention'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(3341723590971059)
        ,p_plug_display_sequence=>14
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows=>15
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_plug_display_condition_type=>'PLSQL_EXPRESSION'
        ,p_plug_display_when_condition=>':P10_TABSET1 = ''TABSET1_TAB4'' AND NVL(:P0_LAO_RESTRICTED,''N'') = ''N'''
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(13588606867642407)
        ,p_plug_name=>'SUMMARY_TAB_MESSAGE'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(6452830739005671)
        ,p_plug_display_sequence=>70
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_source=>'There are no assessments containing OGP or OVP scores available to view for this offender.'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows=>15
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_plug_display_condition_type=>'PLSQL_EXPRESSION'
        ,p_plug_display_when_condition=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            ':P10_TABSET1 = ''TABSET1_TAB5'' AND NVL(:P0_LAO_RESTRICTED,''N'') = ''N''',
            'and not mod_off030_pkg.p10_display_ogp_ovp_scores'))
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(8141332053796520)
        ,p_button_sequence=>80
        ,p_button_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_button_name=>'P10_UNKNOWN_PNC'
        ,p_button_static_id=>'P10_UNKNOWN_PNC'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(5671908814747758)
        ,p_button_image_alt=>'Unknown PNC'
        ,p_button_position=>'BODY'
        ,p_button_execute_validations=>'N'
        ,p_button_condition=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'mod_off030_pkg.user_is_non_controlling_owner = FALSE AND ',
            'NVL(:P0_READONLY,''N'') = ''N'' AND ',
            'app_ctx_pkg.get(''SERVICE_ELM'') != ''HMPS'' AND',
            'assessment_utils_pkg.gc_force_crn = FALSE OR ( NVL(:P0_READONLY,''N'') = ''N'' AND app_ctx_pkg.get(''SERVICE_ELM'') = ''HMPS'' AND authorisation_pkg.is_function_authorised(p_ref_function_code=>''EOPPU'') )'))
        ,p_button_condition_type=>'PLSQL_EXPRESSION'
        ,p_button_cattributes=>'onclick="setUnknownPNC()" tabindex=100'
        ,p_grid_new_grid=>false
        ,p_grid_new_row=>'N'
        ,p_grid_new_column=>'N'
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(3543516493475113)
        ,p_button_sequence=>250
        ,p_button_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_button_name=>'P10_ADD_PRISON_TEAM'
        ,p_button_static_id=>'P10_ADD_PRISON_TEAM'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Add/Edit/View Teams'
        ,p_button_position=>'BODY'
        ,p_button_alignment=>'LEFT'
        ,p_button_condition=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            ':P0_ACCESS_MODE = offenders_pkg.gc_v_full_access AND',
            'app_ctx_pkg.get(''SERVICE_ELM'') = ''HMPS'' /* Prison User */'))
        ,p_button_condition_type=>'PLSQL_EXPRESSION'
        ,p_button_cattributes=>'tabindex=1182'
        ,p_request_source=>'ADD_PRISON_TEAM'
        ,p_request_source_type=>'STATIC'
        ,p_grid_column_attributes=>'ALT="Prison Owner, Add/Edit/View Teams"'
        ,p_grid_new_grid=>false
        ,p_grid_new_row=>'Y'
        ,p_grid_column_span=>1
        ,p_grid_row_span=>1
        ,p_security_scheme=>wwv_flow_api.id(5065329904605144)
        );
end;
/
begin
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(3536807312434639)
        ,p_button_sequence=>270
        ,p_button_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_button_name=>'P10_ADD_PROBATION_TEAM'
        ,p_button_static_id=>'P10_ADD_PROBATION_TEAM'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Add/Edit/View Teams'
        ,p_button_position=>'BODY'
        ,p_button_alignment=>'LEFT'
        ,p_button_condition=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            ':P0_ACCESS_MODE = offenders_pkg.gc_v_full_access AND ',
            'app_ctx_pkg.get(''SERVICE_ELM'') = ''NPS'' /* Probation User */'))
        ,p_button_condition_type=>'PLSQL_EXPRESSION'
        ,p_request_source=>'ADD_PROBATION_TEAM'
        ,p_request_source_type=>'STATIC'
        ,p_grid_column_attributes=>'ALT="Probation Owner, Add/Edit/View Teams"'
        ,p_grid_new_grid=>false
        ,p_grid_new_row=>'Y'
        ,p_grid_column_span=>1
        ,p_grid_row_span=>1
        ,p_grid_column=>4
        ,p_security_scheme=>wwv_flow_api.id(5065329904605144)
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(12256022295739014)
        ,p_button_sequence=>14210
        ,p_button_plug_id=>wwv_flow_api.id(12253521640691554)
        ,p_button_name=>'P10_DELETE_OFFENDER'
        ,p_button_static_id=>'P10_DELETE_OFFENDER'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Delete Offender'
        ,p_button_position=>'BODY'
        ,p_button_alignment=>'LEFT'
        ,p_button_condition=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'mod_OFF030_pkg.p10_display_del_off_button = TRUE AND ',
            ':P10_POTENTIAL_DELETION_DATE is not null'))
        ,p_button_condition_type=>'PLSQL_EXPRESSION'
        ,p_button_cattributes=>'name="P10_DELETE_OFFENDER" tabindex=1525'
        ,p_request_source=>'DELETE_OFFENDER'
        ,p_request_source_type=>'STATIC'
        ,p_grid_new_grid=>false
        ,p_grid_new_row=>'N'
        ,p_grid_new_column=>'Y'
        ,p_grid_column_span=>1
        ,p_grid_row_span=>1
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(2779302414851795)
        ,p_button_sequence=>120
        ,p_button_plug_id=>wwv_flow_api.id(10826303560893536)
        ,p_button_name=>'P10_BT_ENTER_INTBDTTO'
        ,p_button_action=>'REDIRECT_URL'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Enter INTBDTTO'
        ,p_button_position=>'BOTTOM'
        ,p_button_redirect_url=>'javascript:appevent(event, ''ENTER_INTBDTTO'', ''Where a decision is taken to withhold information from the offender it must be stored within this section of OASys. No personal data may be stored within this section unless it is being retained to:\n- Prevent or detect crime \n- Apprehend or prosecute offenders \n- Where disclosing information to the offender would be likely to prejudice these purposes (Data Protection Act 1998 section 29) '');'||wwv_flow.LF||
                                ''
        ,p_button_condition=>'RETURN mod_OFF030_pkg.p10_display_INTBDTTO_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        ,p_security_scheme=>wwv_flow_api.id(11371114710319837)
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(2841702206830753)
        ,p_button_sequence=>210
        ,p_button_plug_id=>wwv_flow_api.id(2837929424403404)
        ,p_button_name=>'ADD_NEW_ALIAS'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Add New Alias'
        ,p_button_position=>'BOTTOM'
        ,p_button_condition=>'return mod_off030_pkg.p10_display_alias_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(2777914628851790)
        ,p_button_sequence=>10
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_SAVE'
        ,p_button_action=>'REDIRECT_URL'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790204340320563)
        ,p_button_image_alt=>'Save'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_button_redirect_url=>'javascript:&AI_NAV_SYS.(''SAVE'');'
        ,p_button_condition=>'RETURN mod_OFF030_pkg.p10_display_save_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        ,p_security_scheme=>wwv_flow_api.id(11371114710319837)
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(2778128587851794)
        ,p_button_sequence=>20
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_CLOSE'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790303721320563)
        ,p_button_image_alt=>'Close'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_button_execute_validations=>'N'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(11159610374967841)
        ,p_button_sequence=>100
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_GET_LAST_CASE'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Get Last Case from Delius'
        ,p_button_position=>'REGION_TEMPLATE_PREVIOUS'
        ,p_button_condition=>'RETURN mod_OFF030_pkg.p10_display_get_last_case;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        ,p_security_scheme=>wwv_flow_api.id(1361412012250723)
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(2778904353851794)
        ,p_button_sequence=>110
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_SEARCH_CMS'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Update From CMS'
        ,p_button_position=>'REGION_TEMPLATE_PREVIOUS'
        ,p_button_condition=>'RETURN mod_OFF030_pkg.p10_display_UPDATE_CMS_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        ,p_security_scheme=>wwv_flow_api.id(1361412012250723)
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(2799207889517209)
        ,p_button_sequence=>130
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_PRINT'
        ,p_button_action=>'REDIRECT_URL'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Print'
        ,p_button_position=>'REGION_TEMPLATE_PREVIOUS'
        ,p_button_redirect_url=>'javascript:appdosubmit(''MOD_PRN030::0:P0_PRINT_TYPE::'');'
        ,p_button_condition=>'P0_LAO_RESTRICTED'
        ,p_button_condition2=>'Y'
        ,p_button_condition_type=>'VAL_OF_ITEM_IN_COND_NOT_EQ_COND2'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(2799414815519187)
        ,p_button_sequence=>140
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_CREATE_ASSESSMENT'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Create Assessment'
        ,p_button_position=>'REGION_TEMPLATE_PREVIOUS'
        ,p_button_condition=>'RETURN mod_OFF030_pkg.p10_display_cr_assess_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(5499704364139530)
        ,p_button_sequence=>145
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_CREATE_BCS'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Create BCS'
        ,p_button_position=>'REGION_TEMPLATE_PREVIOUS'
        ,p_button_condition=>'RETURN mod_OFF030_pkg.p10_display_cr_bcs_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        ,p_security_scheme=>wwv_flow_api.id(12858825827458081)
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(2799804210525548)
        ,p_button_sequence=>160
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_REQUEST_TRANS'
        ,p_button_action=>'REDIRECT_URL'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Request Transfer'
        ,p_button_position=>'REGION_TEMPLATE_PREVIOUS'
        ,p_button_redirect_url=>'javascript:&AI_NAV_SYS.(''MOD_TRF010'');'
        ,p_button_condition=>'RETURN mod_OFF030_pkg.p10_display_req_trans_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        ,p_security_scheme=>wwv_flow_api.id(7259628131616558)
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(2777531876370116)
        ,p_button_sequence=>165
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_REQ_AUT_TRANS'
        ,p_button_action=>'REDIRECT_URL'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Request Automatic Transfer'
        ,p_button_position=>'REGION_TEMPLATE_PREVIOUS'
        ,p_button_redirect_url=>'javascript:&AI_NAV_SYS.(''MOD_TRF010'');'
        ,p_button_condition=>'RETURN mod_OFF030_pkg.p10_display_req_trans_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        ,p_security_scheme=>wwv_flow_api.id(2777313126363603)
        ,p_button_comment=>'Request Automatic Transfer'
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(2827529481807225)
        ,p_button_sequence=>170
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_LAO'
        ,p_button_action=>'REDIRECT_URL'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'LAO'
        ,p_button_position=>'REGION_TEMPLATE_PREVIOUS'
        ,p_button_redirect_url=>'javascript:&AI_NAV_SYS.(''PAGE_90::90::'');'
        ,p_button_condition=>'mod_OFF030_pkg.p10_display_LAO_button = TRUE AND  :P10_LAO_ENABLED_FLAG IS NOT NULL'
        ,p_button_condition_type=>'PLSQL_EXPRESSION'
        ,p_grid_new_grid=>false
        ,p_security_scheme=>wwv_flow_api.id(1345109290433762)
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(2827824895824873)
        ,p_button_sequence=>180
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_RFI'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'RFI'
        ,p_button_position=>'REGION_TEMPLATE_PREVIOUS'
        ,p_button_condition=>'mod_OFF030_pkg.p10_display_RFI_button = TRUE'
        ,p_button_condition_type=>'PLSQL_EXPRESSION'
        ,p_grid_new_grid=>false
        ,p_security_scheme=>wwv_flow_api.id(1345929469496385)
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(2828008403829550)
        ,p_button_sequence=>190
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_DEMERGE'
        ,p_button_action=>'REDIRECT_URL'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Demerge'
        ,p_button_position=>'REGION_TEMPLATE_PREVIOUS'
        ,p_button_redirect_url=>'javascript:&AI_NAV_SYS.(''MOD_MRG010:::'');'
        ,p_button_condition=>'RETURN mod_OFF030_pkg.p10_display_demerge_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(7693208822577285)
        ,p_button_sequence=>195
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_DEMERGE_ADV'
        ,p_button_action=>'REDIRECT_URL'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Demerge'
        ,p_button_position=>'REGION_TEMPLATE_PREVIOUS'
        ,p_button_redirect_url=>'javascript:&AI_NAV_SYS.(''MOD_MRG020::APP,RP:P20_MODE:OASYS_DEMERGE'');'
        ,p_button_condition=>'RETURN mod_mrg020_pkg.demerge_ass_button_cond;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(2828200569836710)
        ,p_button_sequence=>200
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_RFA'
        ,p_button_action=>'REDIRECT_URL'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'RFA'
        ,p_button_position=>'REGION_TEMPLATE_PREVIOUS'
        ,p_button_redirect_url=>'javascript:&AI_NAV_SYS.(''PAGE_60::60::'');'
        ,p_button_condition=>'RETURN mod_OFF030_pkg.p10_display_rfa_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        ,p_security_scheme=>wwv_flow_api.id(13484016745293790)
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(1554103918761192)
        ,p_button_sequence=>220
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_RESCIND_RFA'
        ,p_button_action=>'REDIRECT_URL'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Rescind RFA'
        ,p_button_position=>'REGION_TEMPLATE_PREVIOUS'
        ,p_button_redirect_url=>'javascript:&AI_NAV_SYS.(''PAGE_70::70::'');'
        ,p_button_condition=>'RETURN mod_OFF030_pkg.p10_display_rfa_rescind_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        ,p_security_scheme=>wwv_flow_api.id(13484625318315252)
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(7786413795072488)
        ,p_button_sequence=>230
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_VIEW_CURRENT_RFA'
        ,p_button_action=>'REDIRECT_URL'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'View Current RFA'
        ,p_button_position=>'REGION_TEMPLATE_PREVIOUS'
        ,p_button_redirect_url=>'javascript:&AI_NAV_SYS.(''PAGE_80::80::'');'
        ,p_button_condition=>'RETURN mod_OFF030_pkg.p10_display_current_rfa_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(3337520381498281)
        ,p_button_sequence=>240
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_CREATE_RESETP'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Create Review Resettlement Plan '
        ,p_button_position=>'REGION_TEMPLATE_PREVIOUS'
        ,p_button_condition=>'RETURN mod_OFF030_pkg.p10_display_cr_resetp_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(7884432443618289)
        ,p_branch_action=>'50'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>10
        ,p_branch_condition_type=>'PLSQL_EXPRESSION'
        ,p_branch_condition=>':REQUEST = ''CONFIRMENTER_INTBDTTO'' OR :REQUEST = ''ENTER_INTBDTTO'' OR :REQUEST LIKE ''PAGE_50%'''
        ,p_branch_comment=>'Created 28-SEP-2010 15:49 by ZIPPYMICHAEL'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(7891606208941957)
        ,p_branch_action=>'500'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>20
        ,p_branch_condition_type=>'PLSQL_EXPRESSION'
        ,p_branch_condition=>':REQUEST LIKE ''LOCKREQ_OASYS_SET_PK=%'''
        ,p_branch_comment=>'Created 28-SEP-2010 16:43 by ZIPPYMICHAEL'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(7891822484946598)
        ,p_branch_action=>'60'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>30
        ,p_branch_condition_type=>'PLSQL_EXPRESSION'
        ,p_branch_condition=>':REQUEST LIKE ''PAGE_60%'''
        ,p_branch_comment=>'Created 28-SEP-2010 16:44 by ZIPPYMICHAEL'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(7892315043982323)
        ,p_branch_action=>'20'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>40
        ,p_branch_condition_type=>'PLSQL_EXPRESSION'
        ,p_branch_condition=>':REQUEST = ''ADD_NEW_ALIAS'' OR :REQUEST LIKE ''PAGE_20%'''
        ,p_branch_comment=>'Created 28-SEP-2010 16:50 by ZIPPYMICHAEL'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(7893031451996539)
        ,p_branch_action=>'30'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>50
        ,p_branch_condition_type=>'REQUEST_IN_CONDITION'
        ,p_branch_condition=>'ADD_PRISON_TEAM,ADD_PROBATION_TEAM'
        ,p_branch_comment=>'Created 28-SEP-2010 16:52 by ZIPPYMICHAEL'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(7982606360865394)
        ,p_branch_action=>'70'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>60
        ,p_branch_condition_type=>'PLSQL_EXPRESSION'
        ,p_branch_condition=>':REQUEST LIKE ''PAGE_70%'''
        ,p_branch_comment=>'Created 30-SEP-2010 15:44 by ZIPPYB'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(8425731866263856)
        ,p_branch_action=>'80'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>70
        ,p_branch_condition_type=>'PLSQL_EXPRESSION'
        ,p_branch_condition=>':REQUEST LIKE ''PAGE_80%'''
        ,p_branch_comment=>'Created 08-OCT-2010 10:57 by ZIPPYB'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(8681210531074657)
        ,p_branch_action=>'90'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>90
        ,p_branch_condition_type=>'PLSQL_EXPRESSION'
        ,p_branch_condition=>':REQUEST LIKE ''PAGE_90%'''
        ,p_branch_comment=>'Created 11-OCT-2010 16:12 by ZIPPYB'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(7872816439008105)
        ,p_branch_action=>'return app_navigation_pkg.navurl(:AI_JUMPTO_NAV_URL);'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_FUNCTION_RETURNING_URL'
        ,p_branch_sequence=>9999
        ,p_save_state_before_branch_yn=>'Y'
        ,p_branch_comment=>'Created 28-SEP-2010 14:08 by ZIPPYMICHAEL'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1545900877892592)
        ,p_name=>'P10_LOCAL_AUTHORITY_CAT'
        ,p_item_sequence=>290
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1973956893792771)
        ,p_name=>'P10_LIFE_IND'
        ,p_item_sequence=>20
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Lifer'
        ,p_display_as=>'NATIVE_CHECKBOX'
        ,p_lov=>'STATIC2:;Y'
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'onclick="return false"  tabindex=85'
        ,p_begin_on_new_line=>'N'
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'1'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2025555310637706)
        ,p_name=>'P10_OFFENDER_PK_1'
        ,p_item_sequence=>10
        ,p_item_plug_id=>wwv_flow_api.id(2025110435637702)
        ,p_source=>'P10_OFFENDER_PK'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2025876557637709)
        ,p_name=>'P10_NEW_SENTENCE_PLAN_BASE_URL'
        ,p_item_sequence=>20
        ,p_item_plug_id=>wwv_flow_api.id(2025110435637702)
        ,p_use_cache_before_default=>'NO'
        ,p_source=>'select ELEMENTS_PKG.GET_DESC(''SENTENCE_PLAN'',''SENTENCE_PLAN_WEBAPP_URL'') from dual'
        ,p_source_type=>'QUERY'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2376001328403964)
        ,p_name=>'P10_HOST_CRC'
        ,p_item_sequence=>280
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Prison Owner </span>Host CRC'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>42
        ,p_cMaxlength=>4000
        ,p_tag_attributes=>' class="readOnlyFieldLeft" READONLY'
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2376224391408457)
        ,p_name=>'P10_RESETTLEMENT_OFFICER'
        ,p_item_sequence=>290
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Prison Owner </span>Resettlement Officer'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>42
        ,p_cMaxlength=>4000
        ,p_tag_attributes=>' class="readOnlyFieldLeft" READONLY'
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2407001456084174)
        ,p_name=>'P10_HOST_CRC_CODE'
        ,p_item_sequence=>265
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'Host Crc Code'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795019789320568)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2779500977851795)
        ,p_name=>'P10_ONCE_PER_SESSION'
        ,p_item_sequence=>58
        ,p_prompt=>'Once Per Session'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2779721256851796)
        ,p_name=>'P10_MODE'
        ,p_item_sequence=>68
        ,p_prompt=>'Page Mode'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2779905058851796)
        ,p_name=>'P10_TABSET2'
        ,p_item_sequence=>48
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_prompt=>'Tabset1'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_template=>wwv_flow_api.id(20795019789320568)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2780126069851796)
        ,p_name=>'P10_TABSET1'
        ,p_item_sequence=>38
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_prompt=>'Tabset1'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_template=>wwv_flow_api.id(20795019789320568)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2781505335851798)
        ,p_name=>'P10_INIT'
        ,p_item_sequence=>28
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_prompt=>'Init'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2806131907563616)
        ,p_name=>'P10_OFFENDER_PK'
        ,p_item_sequence=>10
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2806304679563617)
        ,p_name=>'P10_ADDRESS_LAST_UPDATE_DATE'
        ,p_item_sequence=>270
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2806508822563617)
        ,p_name=>'P10_ADDRESS_LINE_1'
        ,p_item_sequence=>30
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_prompt=>'Apt/Flat Number'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>35
        ,p_cMaxlength=>256
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2806720288563617)
        ,p_name=>'P10_ADDRESS_LINE_2'
        ,p_item_sequence=>40
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_prompt=>'Premises Name/Number'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>35
        ,p_cMaxlength=>256
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2806911915563619)
        ,p_name=>'P10_ADDRESS_LINE_3'
        ,p_item_sequence=>50
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_prompt=>'Street'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>35
        ,p_cMaxlength=>256
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2807118579563619)
        ,p_name=>'P10_ADDRESS_LINE_4'
        ,p_item_sequence=>60
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_prompt=>'Locality'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>35
        ,p_cMaxlength=>256
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2807330921563619)
        ,p_name=>'P10_ADDRESS_LINE_5'
        ,p_item_sequence=>70
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_prompt=>'Town'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>35
        ,p_cMaxlength=>256
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2807511798563619)
        ,p_name=>'P10_ADDRESS_POSTCODE'
        ,p_item_sequence=>90
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_prompt=>'Postcode'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>10
        ,p_cMaxlength=>32
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2807727935563619)
        ,p_name=>'P10_DATE_OF_BIRTH'
        ,p_item_sequence=>210
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Date Of Birth'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>10
        ,p_cMaxlength=>10
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" data-dateitemtype="date" tabindex=60'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795309951320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2807912471563620)
        ,p_name=>'P10_FAMILY_NAME'
        ,p_item_sequence=>30
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Surname'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>32
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" tabindex=20'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795309951320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2808127309563620)
        ,p_name=>'P10_NAME_SOUNDSLIKE'
        ,p_item_sequence=>120
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2808317174563620)
        ,p_name=>'P10_FORENAME_1'
        ,p_item_sequence=>100
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Forename'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>32
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" tabindex=30'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795309951320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2808525163563620)
        ,p_name=>'P10_FORENAME_2'
        ,p_item_sequence=>140
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Forename 2'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>32
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" tabindex=40'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2808720071563620)
        ,p_name=>'P10_LIMITED_ACCESS_OFFENDER'
        ,p_item_sequence=>140
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_prompt=>'LAO'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>1
        ,p_cMaxlength=>1
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when_type=>'NEVER'
        ,p_read_only_when_type=>'NEVER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2808921632563621)
        ,p_name=>'P10_ON_TEMP_TRANSFER_UNTIL'
        ,p_item_sequence=>280
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2809120611563621)
        ,p_name=>'P10_PNC'
        ,p_item_sequence=>70
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'PNC'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>25
        ,p_cMaxlength=>32
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" tabindex=90'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>2
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795309951320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2809330284563621)
        ,p_name=>'P10_CMS_PROB_NUMBER'
        ,p_item_sequence=>190
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Probation CRN'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>8
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" tabindex=130'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when=>'P0_LAO_RESTRICTED'
        ,p_display_when2=>'N'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2809530830563621)
        ,p_name=>'P10_CRO_NUMBER'
        ,p_item_sequence=>150
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'CRO'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>12
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" tabindex=110'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
end;
/
begin
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2809913519563622)
        ,p_name=>'P10_PRISON_NUMBER'
        ,p_item_sequence=>260
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Prison LIDS No'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>6
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" tabindex=180'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2810321493563622)
        ,p_name=>'P10_MERGE_PNC_NUMBER'
        ,p_item_sequence=>110
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Merge PNC'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>20
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" tabindex=100 readonly'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when=>'RETURN mod_mrg010_pkg.exist_instigator(p_offender_pk => v(mod_off030_pkg.gc_v_offender_pk));'
        ,p_display_when_type=>'FUNCTION_BODY'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2810502457563623)
        ,p_name=>'P10_DATE_OF_DEATH'
        ,p_item_sequence=>280
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Date Of Death'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>10
        ,p_cMaxlength=>15
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" data-dateitemtype="date" tabindex=200'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when=>'P0_LAO_RESTRICTED'
        ,p_display_when2=>'N'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2810724460563623)
        ,p_name=>'P10_DATE_OF_DEPORTATION'
        ,p_item_sequence=>300
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Date Of Deportation'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>10
        ,p_cMaxlength=>15
        ,p_cAttributes=>'style="white-space:nowrap" id="P10_DATE_OF_DEPORTATION_LABEL"'
        ,p_tag_attributes=>'autocomplete="off" data-dateitemtype="date" tabindex=210'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>5
        ,p_rowspan=>1
        ,p_display_when=>'P0_LAO_RESTRICTED'
        ,p_display_when2=>'N'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2810930222563623)
        ,p_name=>'P10_OFFENDER_MANAGED_IND'
        ,p_item_sequence=>40
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Probation Owner </span>OM Phase II&amp;III'
        ,p_display_as=>'NATIVE_CHECKBOX'
        ,p_lov=>'STATIC2:;Y'
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select a Value --'
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'1'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2811122033563623)
        ,p_name=>'P10_PPO_IND'
        ,p_item_sequence=>50
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_prompt=>'PPO'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>1
        ,p_cMaxlength=>1
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2811327966563624)
        ,p_name=>'P10_NAT_STANDARDS_SUSP'
        ,p_item_sequence=>130
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2811523192563624)
        ,p_name=>'P10_REMAND_IND'
        ,p_item_sequence=>30
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Prison Owner </span>Remand Indicator'
        ,p_display_as=>'NATIVE_CHECKBOX'
        ,p_lov=>'STATIC2:;Y'
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_cattributes_element=>' class="checkbox"'
        ,p_tag_attributes=>' class="readOnlyFieldLeft" READONLY'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'1'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2812313189563624)
        ,p_name=>'P10_ETHNIC_CATEGORY_CAT'
        ,p_item_sequence=>140
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2812516512563625)
        ,p_name=>'P10_GENDER_ELM'
        ,p_item_sequence=>250
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Gender'
        ,p_display_as=>'NATIVE_SELECT_LIST'
        ,p_lov=>'RETURN elements_pkg.return_lov (''GENDER'', :P10_GENDER_ELM);'
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select --'
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'tabindex=70'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795309951320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NONE'
        ,p_attribute_02=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2812701798563625)
        ,p_name=>'P10_GENDER_CAT'
        ,p_item_sequence=>150
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2812901511563625)
        ,p_name=>'P10_OFFENCE_ELM'
        ,p_item_sequence=>160
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2813120807563625)
        ,p_name=>'P10_OFFENCE_CAT'
        ,p_item_sequence=>170
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2813306392563625)
        ,p_name=>'P10_OWNING_SERVICE_ELM'
        ,p_item_sequence=>180
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2813517683563625)
        ,p_name=>'P10_OWNING_SERVICE_CAT'
        ,p_item_sequence=>190
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2813722303563626)
        ,p_name=>'P10_RISK_TO_OTHERS_ELM'
        ,p_item_sequence=>60
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_prompt=>'ROSH'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>1
        ,p_cMaxlength=>1
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when_type=>'NEVER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2813900545563626)
        ,p_name=>'P10_RISK_TO_OTHERS_CAT'
        ,p_item_sequence=>200
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2814130573563626)
        ,p_name=>'P10_RISK_TO_SELF_ELM'
        ,p_item_sequence=>70
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_prompt=>'Risk of Suicide/Self Harm'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>1
        ,p_cMaxlength=>1
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when_type=>'NEVER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'Y'
        ,p_attribute_03=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2814314070563626)
        ,p_name=>'P10_RISK_TO_SELF_CAT'
        ,p_item_sequence=>210
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2814528370563626)
        ,p_name=>'P10_OFFENDER_HISTORIC_ELM'
        ,p_item_sequence=>220
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2814719921563626)
        ,p_name=>'P10_OFFENDER_HISTORIC_CAT'
        ,p_item_sequence=>230
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2814923349563627)
        ,p_name=>'P10_LOCAL_AUTHORITY_ELM'
        ,p_item_sequence=>110
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_prompt=>'Local Authority'
        ,p_display_as=>'NATIVE_SELECT_LIST'
        ,p_lov=>'RETURN elements_pkg.return_lov(''LOCAL_AUTHORITY'',:P10_LOCAL_AUTHORITY_ELM);'
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select Local Authority --'
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NONE'
        ,p_attribute_02=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2815123383563627)
        ,p_name=>'P10_DECEASED_IND'
        ,p_item_sequence=>270
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Deceased'
        ,p_display_as=>'NATIVE_CHECKBOX'
        ,p_lov=>'STATIC2:;Y'
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'tabindex=190'
        ,p_tag_attributes2=>'checked="false"  class="checkbox"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>3
        ,p_rowspan=>1
        ,p_display_when=>'P0_LAO_RESTRICTED'
        ,p_display_when2=>'N'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'1'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2815322905563627)
        ,p_name=>'P10_CHECKSUM'
        ,p_item_sequence=>240
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2815712758563627)
        ,p_name=>'P10_OWNING_PRISON_AREA'
        ,p_item_sequence=>250
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2815911601563627)
        ,p_name=>'P10_OWNING_PROBATION_AREA'
        ,p_item_sequence=>260
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2816121274563628)
        ,p_name=>'P10_PRIMARY_LOC_AREA_DESC'
        ,p_item_sequence=>170
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Controlling Owner &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>100
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" tabindex=120'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>5
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'Y'
        ,p_attribute_03=>'Y'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(2816324275563628)
        ,p_name=>'P10_PHYSICAL_LOCATION'
        ,p_item_sequence=>200
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Provider/Est Location'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>100
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'class="readOnlyFieldLeft" READONLY tabindex=140'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>5
        ,p_rowspan=>1
        ,p_display_when=>'P0_LAO_RESTRICTED'
        ,p_display_when2=>'N'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3038408454852511)
        ,p_name=>'P10_TELEPHONE_NUMBER'
        ,p_item_sequence=>100
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_prompt=>'Telephone'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>35
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3039319667865277)
        ,p_name=>'P10_CMS_PRIS_NUMBER'
        ,p_item_sequence=>220
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Prison NOMIS No'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'tabindex=150'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when=>'P0_LAO_RESTRICTED'
        ,p_display_when2=>'N'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3211732065991441)
        ,p_name=>'P10_NORMAL_REVIEW_PERIOD'
        ,p_item_sequence=>550
        ,p_item_plug_id=>wwv_flow_api.id(2772921042851781)
        ,p_prompt=>'Normal Review Period (Months)'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>2
        ,p_cMaxlength=>2
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" '
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when_type=>'NEVER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'Y'
        ,p_attribute_03=>'Y'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        ,p_item_comment=>'The Default value is 16'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3211925616998963)
        ,p_name=>'P10_REVIEW_DATE'
        ,p_item_sequence=>560
        ,p_item_plug_id=>wwv_flow_api.id(2772921042851781)
        ,p_prompt=>'Review Due Date'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>15
        ,p_cMaxlength=>15
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'readonly class="readOnlyFieldLeft" data-dateitemtype="date"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3215618746101069)
        ,p_name=>'P10_PSR_AREA'
        ,p_item_sequence=>661
        ,p_item_plug_id=>wwv_flow_api.id(3212125747008462)
        ,p_prompt=>'Additional Provider'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'Y'
        ,p_attribute_03=>'Y'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3215814029109164)
        ,p_name=>'P10_PSR_TEL'
        ,p_item_sequence=>662
        ,p_item_plug_id=>wwv_flow_api.id(3212125747008462)
        ,p_prompt=>'Telephone'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3216223379111897)
        ,p_name=>'P10_PSR_TEAM'
        ,p_item_sequence=>663
        ,p_item_plug_id=>wwv_flow_api.id(3212125747008462)
        ,p_prompt=>'Additional Provider Team '
        ,p_display_as=>'NATIVE_SELECT_LIST'
        ,p_lov=>'return mod_off030_pkg.p10_lov_psr_team;'
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select a Value --'
        ,p_lov_null_value=>'0'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NONE'
        ,p_attribute_02=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3216613121118372)
        ,p_name=>'P10_PSR_EMAIL'
        ,p_item_sequence=>664
        ,p_item_plug_id=>wwv_flow_api.id(3212125747008462)
        ,p_prompt=>'Email'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>40
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3217101477124516)
        ,p_name=>'P10_PSR_ASSESSOR'
        ,p_item_sequence=>665
        ,p_item_plug_id=>wwv_flow_api.id(3212125747008462)
        ,p_prompt=>'Addtional Provider Assessor'
        ,p_display_as=>'NATIVE_POPUP_LOV'
        ,p_lov=>'return mod_off030_pkg.p10_lov_psr_assessor;'
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select a Value --'
        ,p_lov_null_value=>'0'
        ,p_lov_cascade_parent_items=>'P10_PSR_TEAM'
        ,p_ajax_optimize_refresh=>'Y'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NOT_ENTERABLE'
        ,p_attribute_02=>'FIRST_ROWSET'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3217521217130181)
        ,p_name=>'P10_AWAITING_PROB_OWNER'
        ,p_item_sequence=>310
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Probation Owner </span>Awaiting Probation Owner'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'Y'
        ,p_attribute_03=>'Y'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3218130221132782)
        ,p_name=>'P10_AWAITING_PROB_TEAM'
        ,p_item_sequence=>330
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Probation Owner </span>Awaiting Probation Team'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_grid_column=>3
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'Y'
        ,p_attribute_03=>'Y'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3218305764135185)
        ,p_name=>'P10_AWAITING_PROB_MAN'
        ,p_item_sequence=>350
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Probation Owner </span>Awaiting Probation Offender Manager'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_grid_column=>3
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'Y'
        ,p_attribute_03=>'Y'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3218518924139020)
        ,p_name=>'P10_AWAITING_PRISON_OWNER'
        ,p_item_sequence=>300
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Prison Owner </span>Awaiting Prison Owner'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'Y'
        ,p_attribute_03=>'Y'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3220403003210099)
        ,p_name=>'P10_CONTROLLING_OWNER'
        ,p_item_sequence=>70
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_item_default=>'1'
        ,p_display_as=>'NATIVE_RADIOGROUP'
        ,p_lov=>'STATIC2:CONTROLLING OWNER;1,CONTROLLING OWNER;2'
        ,p_cSize=>45
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>' tabindex=1205'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_alignment=>'RIGHT'
        ,p_display_when_type=>'NEVER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_escape_on_http_output=>'N'
        ,p_attribute_01=>'2'
        ,p_attribute_02=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3221205689229772)
        ,p_name=>'P10_PRISON_CLUSTER'
        ,p_item_sequence=>80
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Probation Owner </span>Prison Cluster'
        ,p_display_as=>'NATIVE_SELECT_LIST'
        ,p_lov=>'STATIC2:CLUSTER A;1'
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select a Value --'
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>' tabindex=1107'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when_type=>'NEVER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NONE'
        ,p_attribute_02=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3221418502233537)
        ,p_name=>'P10_PRISON_EST'
        ,p_item_sequence=>90
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Prison Owner </span>Prison Establishment'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'Y'
        ,p_attribute_03=>'Y'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3221631662237274)
        ,p_name=>'P10_OFF_SUP_TEAM'
        ,p_item_sequence=>130
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Prison Owner </span>Offender Supervisor Team'
        ,p_display_as=>'NATIVE_POPUP_LOV'
        ,p_lov=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'RETURN mod_off030_pkg.pris_off_man_team_lov;',
            '',
            '',
            ''))
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select a Value --'
        ,p_lov_cascade_parent_items=>'P10_PRIS_LDU'
        ,p_ajax_items_to_submit=>'P10_PRISON_EST_CODE'
        ,p_ajax_optimize_refresh=>'Y'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NOT_ENTERABLE'
        ,p_attribute_02=>'FIRST_ROWSET'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3222700457275561)
        ,p_name=>'P10_OFF_SUP'
        ,p_item_sequence=>150
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Prison Owner </span>Offender Supervisor'
        ,p_display_as=>'NATIVE_POPUP_LOV'
        ,p_lov=>'RETURN mod_off030_pkg.construct_supervisor_lov;'
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select a Value --'
        ,p_lov_cascade_parent_items=>'P10_PRIS_LDU,P10_OFF_SUP_TEAM'
        ,p_ajax_optimize_refresh=>'Y'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NOT_ENTERABLE'
        ,p_attribute_02=>'FIRST_ROWSET'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3224414318355309)
        ,p_name=>'P10_OFF_SUP_POS'
        ,p_item_sequence=>170
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Prison Owner </span>Offender Supervisor Position'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3224623322357930)
        ,p_name=>'P10_PRISON_TEL_NO'
        ,p_item_sequence=>190
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Prison Owner </span>Telephone'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3224800251360711)
        ,p_name=>'P10_PRISON_EMAIL'
        ,p_item_sequence=>210
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Prison Owner </span>Email'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3225010640363736)
        ,p_name=>'P10_PRISON_ADD_TEAMS'
        ,p_item_sequence=>230
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Prison Owner </span>Additional Teams'
        ,p_display_as=>'NATIVE_TEXTAREA'
        ,p_cSize=>47
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft expand" READONLY'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_03=>'N'
        ,p_attribute_04=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3230423903499977)
        ,p_name=>'P10_PROB_AREA'
        ,p_item_sequence=>100
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Probation Owner </span>Probation Provider'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'Y'
        ,p_attribute_03=>'Y'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3230716069507236)
        ,p_name=>'P10_PROB_LDU'
        ,p_item_sequence=>120
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Probation Owner </span>Probation LDU'
        ,p_display_as=>'NATIVE_POPUP_LOV'
        ,p_lov=>'RETURN mod_off030_pkg.construct_prob_ldu_lov;'
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select a Value --'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NOT_ENTERABLE'
        ,p_attribute_02=>'FIRST_ROWSET'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3231308581514515)
        ,p_name=>'P10_OFF_MAN_TEAM'
        ,p_item_sequence=>140
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Probation Owner </span>Offender Manager Team'
        ,p_display_as=>'NATIVE_POPUP_LOV'
        ,p_lov=>'RETURN mod_off030_pkg.construct_off_man_team_lov;'
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select a Value --'
        ,p_lov_cascade_parent_items=>'P10_PROB_LDU'
        ,p_ajax_items_to_submit=>'P10_OWNING_PROBATION_AREA'
        ,p_ajax_optimize_refresh=>'Y'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NOT_ENTERABLE'
        ,p_attribute_02=>'FIRST_ROWSET'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3231724165518973)
        ,p_name=>'P10_OFF_MAN_POS'
        ,p_item_sequence=>180
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Probation Owner </span>Offender Manager Position'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3232032476521371)
        ,p_name=>'P10_PROB_TEL_NO'
        ,p_item_sequence=>200
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Probation Owner </span>Telephone'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3232807673523675)
        ,p_name=>'P10_PROB_EMAIL'
        ,p_item_sequence=>220
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Probation Owner </span>Email'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
end;
/
begin
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3233115984526112)
        ,p_name=>'P10_PROB_ADD_TEAMS'
        ,p_item_sequence=>240
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Probation Owner </span>Additional Teams'
        ,p_display_as=>'NATIVE_TEXTAREA'
        ,p_cSize=>47
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft expand" READONLY'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_03=>'N'
        ,p_attribute_04=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3234025812538345)
        ,p_name=>'P10_OFF_MANAGER'
        ,p_item_sequence=>160
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Probation Owner </span>Offender Manager'
        ,p_display_as=>'NATIVE_POPUP_LOV'
        ,p_lov=>'RETURN mod_off030_pkg.construct_off_manager_lov;'
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select a Value --'
        ,p_lov_cascade_parent_items=>'P10_PROB_LDU,P10_OFF_MAN_TEAM'
        ,p_ajax_optimize_refresh=>'Y'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NOT_ENTERABLE'
        ,p_attribute_02=>'FIRST_ROWSET'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3405918245858972)
        ,p_name=>'P10_LAO_TEL_NO'
        ,p_item_sequence=>310
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'LAO Contact'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>130
        ,p_cMaxlength=>100
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'class="readOnlyFieldLeft"'
        ,p_colspan=>8
        ,p_rowspan=>1
        ,p_display_when=>'P10_LIMITED_ACCESS_OFFENDER'
        ,p_display_when2=>'Y'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'Y'
        ,p_attribute_03=>'Y'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3421610467970297)
        ,p_name=>'P10_SPACE4'
        ,p_item_sequence=>260
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>45
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3421921698970298)
        ,p_name=>'P10_SPACE2'
        ,p_item_sequence=>320
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>45
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3453323767059312)
        ,p_name=>'P10_SPACE3'
        ,p_item_sequence=>340
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>45
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3556919254750292)
        ,p_name=>'P10_CONT_OWNER_PROB'
        ,p_item_sequence=>60
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Probation Owner </span>Controlling Owner Probation'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>1
        ,p_cMaxlength=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'Y'
        ,p_attribute_03=>'Y'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3558418823769100)
        ,p_name=>'P10_CONT_OWNER_PRIS'
        ,p_item_sequence=>50
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Prison Owner </span>Controlling Owner Prison'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>1
        ,p_cMaxlength=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'Y'
        ,p_attribute_03=>'Y'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(4240323783772862)
        ,p_name=>'P10_ICONS'
        ,p_item_sequence=>90
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_display_as=>'NATIVE_DISPLAY_ONLY'
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>3
        ,p_rowspan=>1
        ,p_protection_level=>'I'
        ,p_escape_on_http_output=>'N'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'VALUE'
        ,p_attribute_04=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(5553820447784081)
        ,p_name=>'P10_ETHNIC_CATEGORY_ELM'
        ,p_item_sequence=>290
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Ethnic Category'
        ,p_display_as=>'NATIVE_SELECT_LIST'
        ,p_lov=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'RETURN elements_pkg.return_lov (''ETHNIC_CATEGORY'', :P10_ETHNIC_CATEGORY_ELM);',
            ''))
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select --'
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'style="width: 450px" tabindex=80'
        ,p_colspan=>3
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NONE'
        ,p_attribute_02=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6259521987582906)
        ,p_name=>'P10_TITLE_ELM'
        ,p_item_sequence=>10
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Title'
        ,p_display_as=>'NATIVE_SELECT_LIST'
        ,p_lov=>'RETURN elements_pkg.return_lov (''TITLE'', :P10_TITLE_ELM);'
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select --'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'tabindex=10'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NONE'
        ,p_attribute_02=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6266216178719019)
        ,p_name=>'P10_PRISON_EST_CODE'
        ,p_item_sequence=>360
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>45
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6416922509772216)
        ,p_name=>'P10_DOB_MAND'
        ,p_item_sequence=>270
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6417229781774278)
        ,p_name=>'P10_ADDRESS_MAND'
        ,p_item_sequence=>280
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6417403939776307)
        ,p_name=>'P10_POSTCODE_MAND'
        ,p_item_sequence=>290
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6417610519778228)
        ,p_name=>'P10_ETHNIC_MAND'
        ,p_item_sequence=>300
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6477217219831946)
        ,p_name=>'P10_NFA_IND'
        ,p_item_sequence=>20
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_prompt=>'No Fixed Abode'
        ,p_display_as=>'NATIVE_CHECKBOX'
        ,p_lov=>'STATIC2:;Y'
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'class="checkbox"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_escape_on_http_output=>'N'
        ,p_attribute_01=>'1'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6617500303749531)
        ,p_name=>'P10_LAO_ENABLED_FLAG'
        ,p_item_sequence=>310
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6811120036955171)
        ,p_name=>'P10_PRIMARY_LOCATION_AREA'
        ,p_item_sequence=>320
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_prompt=>'Owning Area/Est'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>40
        ,p_cMaxlength=>100
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6991104670986238)
        ,p_name=>'P10_LOCK_ASS_OASYS_PK'
        ,p_item_sequence=>14140
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_item_default=>'0'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(7130318148674661)
        ,p_name=>'P10_TRUSTEE_ACCESS_YN'
        ,p_item_sequence=>14170
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_item_default=>'N'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>4000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(8171015709730146)
        ,p_name=>'P10_FORENAME_3'
        ,p_item_sequence=>180
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Forename 3'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>32
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'tabindex=50'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(8202814455089328)
        ,p_name=>'P10_ADDRESS_LINE_6'
        ,p_item_sequence=>80
        ,p_item_plug_id=>wwv_flow_api.id(2772529639851778)
        ,p_prompt=>'County'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>35
        ,p_cMaxlength=>256
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(8238132555851486)
        ,p_name=>'P10_REVIEW_REMINDER_DATE'
        ,p_item_sequence=>570
        ,p_item_plug_id=>wwv_flow_api.id(2772921042851781)
        ,p_prompt=>'Review Reminder Date'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>15
        ,p_cMaxlength=>15
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" data-dateitemtype="date" nowrap="nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(8409315177260662)
        ,p_name=>'P10_PSR_AREA_CODE'
        ,p_item_sequence=>14150
        ,p_item_plug_id=>wwv_flow_api.id(3212125747008462)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(8428728674357503)
        ,p_name=>'P10_DECIDER_PNC'
        ,p_item_sequence=>120
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_item_default=>'POTENTIAL DUPLICATE'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'tabindex=101'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when=>'RETURN mod_mrg010_pkg.exist_decider(p_offender_pk => v(mod_off030_pkg.gc_v_offender_pk));'
        ,p_display_when_type=>'FUNCTION_BODY'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(9222929887722894)
        ,p_name=>'P10_AREA_CMS'
        ,p_item_sequence=>100009
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_prompt=>'CMSCMSCMS'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft"'
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795309951320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(9686408716281078)
        ,p_name=>'P10_PRIS_LDU'
        ,p_item_sequence=>110
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<span class="hidden4jaws">Prison Owner </span>Prison LDU'
        ,p_display_as=>'NATIVE_POPUP_LOV'
        ,p_lov=>'RETURN mod_off030_pkg.construct_pris_ldu_lov;'
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select a Value --'
        ,p_cSize=>42
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NOT_ENTERABLE'
        ,p_attribute_02=>'FIRST_ROWSET'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(10931000666776596)
        ,p_name=>'P10_TERM_REMINDER_DATE'
        ,p_item_sequence=>40
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(11219803106828676)
        ,p_name=>'P10_WARNING_MSG_TEXT'
        ,p_item_sequence=>14160
        ,p_item_plug_id=>wwv_flow_api.id(10826303560893536)
        ,p_prompt=>'Warning Msg Text'
        ,p_source=>'''Where a decision is taken to withhold information from the offender it must be stored within this section of OASys. No personal data may be stored within this section unless it is being retained to\n - Prevent or detect crime \n- Apprehend or prosec'
        ||'ute offenders \n- Where disclosing information to the offender would be likely to prejudice these purposes (Data Protection Act 1998 section 29) '''
        ,p_source_type=>'STATIC'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(11440303392642876)
        ,p_name=>'P10_RFI_NOTE_2'
        ,p_item_sequence=>710
        ,p_item_plug_id=>wwv_flow_api.id(11436929263517810)
        ,p_item_default=>'Please open the latest assessment to see all current RFIs'
        ,p_display_as=>'NATIVE_DISPLAY_ONLY'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when=>'RETURN mod_rfi030_pkg.no_exist_off_exist_ass_rfi(p_offender_pk => v(mod_off030_pkg.gc_v_offender_pk));'
        ,p_display_when_type=>'FUNCTION_BODY'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'VALUE'
        ,p_attribute_04=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(11450218452959416)
        ,p_name=>'P10_RFI_NOTE'
        ,p_item_sequence=>700
        ,p_item_plug_id=>wwv_flow_api.id(11436929263517810)
        ,p_item_default=>'There are currently no RFIs for this offender'
        ,p_display_as=>'NATIVE_DISPLAY_ONLY'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when=>'RETURN NOT mod_rfi030_pkg.exist_rfi_offender(p_offender_pk => v(mod_off030_pkg.gc_v_offender_pk));'
        ,p_display_when_type=>'FUNCTION_BODY'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'VALUE'
        ,p_attribute_04=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(11591027067890891)
        ,p_name=>'P10_PHYSICAL_LOCATION_OTHER_OLD'
        ,p_item_sequence=>99999
        ,p_item_plug_id=>wwv_flow_api.id(2774115356851782)
        ,p_prompt=>'Other Location'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>35
        ,p_cMaxlength=>100
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'tabindex=220'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>5
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_display_when=>'P0_LAO_RESTRICTED'
        ,p_display_when2=>'N'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(11595315171953747)
        ,p_name=>'P10_PHYSICAL_LOCATION_AREA'
        ,p_item_sequence=>50
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(12064707854549069)
        ,p_name=>'P10_PRISON_HEADING'
        ,p_item_sequence=>13
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<h2>Prison Owner</h2>'
        ,p_display_as=>'NATIVE_DISPLAY_ONLY'
        ,p_cSize=>45
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'VALUE'
        ,p_attribute_04=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(12065125169554009)
        ,p_name=>'P10_PROBATION_HEADING'
        ,p_item_sequence=>14
        ,p_item_plug_id=>wwv_flow_api.id(3212303714011632)
        ,p_prompt=>'<h2>Probation Owner</h2>'
        ,p_display_as=>'NATIVE_DISPLAY_ONLY'
        ,p_cSize=>45
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'VALUE'
        ,p_attribute_04=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(12065812156566675)
        ,p_name=>'P10_CONTEXT_SERVICE_ELM'
        ,p_item_sequence=>14190
        ,p_item_plug_id=>wwv_flow_api.id(5239055396194975)
        ,p_source=>'app_ctx_pkg.get(''SERVICE_ELM'')'
        ,p_source_type=>'FUNCTION'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>4000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(12254920863710210)
        ,p_name=>'P10_POTENTIAL_DELETION_DATE'
        ,p_item_sequence=>14200
        ,p_item_plug_id=>wwv_flow_api.id(12253521640691554)
        ,p_prompt=>'Potential Deletion Date'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>10
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_tag_attributes=>'class="readOnlyFieldLeft" readonly="readonly" tabindex=1520'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_display_when=>'P10_POTENTIAL_DELETION_DATE'
        ,p_display_when_type=>'ITEM_IS_NOT_NULL'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(12256828491788133)
        ,p_name=>'P10_RETAINED_IND'
        ,p_item_sequence=>14220
        ,p_item_plug_id=>wwv_flow_api.id(12253521640691554)
        ,p_prompt=>'Retain Offender Records'
        ,p_display_as=>'NATIVE_CHECKBOX'
        ,p_lov=>'STATIC:;Y'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_read_only_when=>'mod_off030_pkg.p10_disable_data_retention'
        ,p_read_only_when_type=>'PLSQL_EXPRESSION'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'1'
        ,p_attribute_02=>'VERTICAL'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(12257600833808570)
        ,p_name=>'P10_RETAINED_REASON'
        ,p_item_sequence=>14230
        ,p_item_plug_id=>wwv_flow_api.id(12253521640691554)
        ,p_prompt=>'Reason for Retention of Offender Data'
        ,p_display_as=>'NATIVE_TEXTAREA'
        ,p_cSize=>80
        ,p_cHeight=>1
        ,p_tag_attributes=>'class="expand" data-countermax="1024"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_03=>'N'
        ,p_attribute_04=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(12295812623231346)
        ,p_name=>'P10_PNC_COUNT'
        ,p_item_sequence=>60
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_item_default=>'0'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(18482126157382491)
        ,p_name=>'P10_OTHER_LOCATION_IND'
        ,p_item_sequence=>230
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'Other Location'
        ,p_display_as=>'NATIVE_CHECKBOX'
        ,p_lov=>'STATIC2:;Y'
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'tabindex=160'
        ,p_tag_attributes2=>'checked="false"  class="checkbox"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>3
        ,p_rowspan=>1
        ,p_display_when=>'P0_LAO_RESTRICTED'
        ,p_display_when2=>'N'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'1'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(18482305163385933)
        ,p_name=>'P10_PHYSICAL_LOCATION_OTHER'
        ,p_item_sequence=>240
        ,p_item_plug_id=>wwv_flow_api.id(2805805081563614)
        ,p_prompt=>'<span class="hidden4jaws">OTHER LOCATION DESCRIPTION</span>'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>10
        ,p_cMaxlength=>100
        ,p_cAttributes=>'style="white-space:nowrap" '
        ,p_tag_attributes=>'tabindex=170'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when=>'P0_LAO_RESTRICTED'
        ,p_display_when2=>'N'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_validation(
            p_id=>wwv_flow_api.id(11096430210562090)
        ,p_validation_name=>'p10_validate_transfer_request'
        ,p_validation_sequence=>5
        ,p_validation=>'RETURN mod_off030_pkg.p10_validate_transfer_request;'
        ,p_validation_type=>'FUNC_BODY_RETURNING_ERR_TEXT'
        ,p_always_execute=>'N'
        ,p_validation_condition=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'RETURN ( :REQUEST LIKE ''%MOD_TRF%''',
            '        OR  :REQUEST LIKE ''PAGE_60%'');'))
        ,p_validation_condition_type=>'FUNCTION_BODY'
        ,p_error_display_location=>'INLINE_IN_NOTIFICATION'
        );
    wwv_flow_api.create_page_validation(
            p_id=>wwv_flow_api.id(5571427952278289)
        ,p_validation_name=>'P10_VP_VALIDATE_OFFENDER'
        ,p_validation_sequence=>10
        ,p_validation=>'RETURN mod_OFF030_pkg.p10_vp_validate_offender;'
        ,p_validation_type=>'FUNC_BODY_RETURNING_ERR_TEXT'
        ,p_always_execute=>'N'
        ,p_error_display_location=>'INLINE_IN_NOTIFICATION'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(12487627882081907)
        ,p_name=>'enable_fields_on_submit'
        ,p_event_sequence=>25
        ,p_bind_type=>'one'
        ,p_bind_event_type=>'apexbeforepagesubmit'
        ,p_da_event_comment=>'These fields need to be enabled before submit so they can be populated, subsequent dynamic actions will change these to be readonly as appropriate.'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(12487926275081908)
        ,p_event_id=>wwv_flow_api.id(12487627882081907)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_REMAND_IND,P10_OFFENDER_MANAGED_IND,P10_PRIS_LDU,P10_PROB_LDU,P10_OFF_SUP_TEAM,P10_OFF_MAN_TEAM,P10_OFF_SUP,P10_OFF_MANAGER,P10_NFA_IND,P10_DECEASED_IND,P10_OTHER_LOCATION_IND'
        ,p_attribute_01=>'unsetreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(2534905248740859)
        ,p_name=>'set_remand_ind_readonly'
        ,p_event_sequence=>40
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_da_event_comment=>'This field is enabled before submit so that it can be populated if required.  It will always then be set to readonly in the page load.'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(2535229058740878)
        ,p_event_id=>wwv_flow_api.id(2534905248740859)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_REMAND_IND'
        ,p_attribute_01=>'setreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(2535313344752606)
        ,p_name=>'ENABLE_RO_ITEMS_ON_SUBMIT'
        ,p_event_sequence=>50
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'apexbeforepagesubmit'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>'mod_off030_pkg.user_is_non_controlling_owner = TRUE OR NVL(:P0_READONLY,''N'') != ''N'''
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(2535603610752606)
        ,p_event_id=>wwv_flow_api.id(2535313344752606)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_ENABLE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_FAMILY_NAME,P10_FORENAME_1,P10_FORENAME_2,P10_FORENAME_3,P10_DATE_OF_BIRTH,P10_GENDER_ELM,P10_ETHNIC_CATEGORY_ELM,P10_PNC,P10_CRO_NUMBER,P10_CMS_PROB_NUMBER,P10_PRISON_NUMBER,P10_PHYSICAL_LOCATION,P10_DECEASED_IND,P10_DATE_OF_DEATH,P10_OTHER_LOCA'
        ||'TION_IND'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(11313822350035816)
        ,p_name=>'NO_FIXED_ABODE'
        ,p_event_sequence=>60
        ,p_triggering_element_type=>'ITEM'
        ,p_triggering_element=>'P10_NFA_IND'
        ,p_triggering_condition_type=>'EQUALS'
        ,p_triggering_expression=>'Y'
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'click'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            ':P0_ACCESS_MODE = ''FULLACCESS'' AND nvl(:P0_READONLY,''N'') = ''N'' AND ',
            '(assessment_utils_pkg.gc_force_crn = FALSE',
            'OR assessment_utils_pkg.gc_is_prison_user = TRUE)'))
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(11348309704044640)
        ,p_event_id=>wwv_flow_api.id(11313822350035816)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_ADDRESS_LINE_1,P10_ADDRESS_LINE_2,P10_ADDRESS_LINE_3,P10_ADDRESS_LINE_4,P10_ADDRESS_LINE_5,P10_ADDRESS_LINE_6,P10_ADDRESS_POSTCODE'
        ,p_attribute_01=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'setreadonly(this.affectedElements);',
            ''))
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(11357822891275483)
        ,p_event_id=>wwv_flow_api.id(11313822350035816)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>20
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_CLEAR'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_ADDRESS_LINE_1,P10_ADDRESS_LINE_2,P10_ADDRESS_LINE_3,P10_ADDRESS_LINE_4,P10_ADDRESS_LINE_5,P10_ADDRESS_LINE_6'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(11358112287281937)
        ,p_event_id=>wwv_flow_api.id(11313822350035816)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>30
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_SET_VALUE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_ADDRESS_POSTCODE'
        ,p_attribute_01=>'STATIC_ASSIGNMENT'
        ,p_attribute_02=>'NF1 1NF'
        ,p_stop_execution_on_error=>'Y'
        ,p_wait_for_result=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(11314309505035827)
        ,p_event_id=>wwv_flow_api.id(11313822350035816)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>40
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_ADDRESS_LINE_1,P10_ADDRESS_LINE_2,P10_ADDRESS_LINE_3,P10_ADDRESS_LINE_4,P10_ADDRESS_LINE_5,P10_ADDRESS_LINE_6,P10_ADDRESS_POSTCODE'
        ,p_attribute_01=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'unsetreadonly(this.affectedElements);',
            ''))
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(74642212674052219)
        ,p_event_id=>wwv_flow_api.id(11313822350035816)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>50
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_CLEAR'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_ADDRESS_POSTCODE'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(74405305425478800)
        ,p_name=>'NO_FIXED_ABODE CRN'
        ,p_event_sequence=>61
        ,p_triggering_element_type=>'ITEM'
        ,p_triggering_element=>'P10_NFA_IND'
        ,p_triggering_condition_type=>'EQUALS'
        ,p_triggering_expression=>'Y'
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'click'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            ':P0_ACCESS_MODE = ''FULLACCESS'' ',
            'AND nvl(:P0_READONLY,''N'') = ''N'' ',
            'AND assessment_utils_pkg.gc_force_crn',
            'AND NOT assessment_utils_pkg.gc_is_prison_user'))
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(74405617442478801)
        ,p_event_id=>wwv_flow_api.id(74405305425478800)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_ADDRESS_LINE_1,P10_ADDRESS_LINE_2,P10_ADDRESS_LINE_3,P10_ADDRESS_LINE_4,P10_ADDRESS_LINE_5,P10_ADDRESS_LINE_6,P10_ADDRESS_POSTCODE'
        ,p_attribute_01=>'setreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
end;
/
begin
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(74406021833486655)
        ,p_event_id=>wwv_flow_api.id(74405305425478800)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>20
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_CLEAR'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_ADDRESS_LINE_1,P10_ADDRESS_LINE_2,P10_ADDRESS_LINE_3,P10_ADDRESS_LINE_4,P10_ADDRESS_LINE_5,P10_ADDRESS_LINE_6'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(74406215579489602)
        ,p_event_id=>wwv_flow_api.id(74405305425478800)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>30
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_SET_VALUE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_ADDRESS_POSTCODE'
        ,p_attribute_01=>'STATIC_ASSIGNMENT'
        ,p_attribute_02=>'NF1 1NF'
        ,p_attribute_09=>'N'
        ,p_stop_execution_on_error=>'Y'
        ,p_wait_for_result=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(74406405443494274)
        ,p_event_id=>wwv_flow_api.id(74405305425478800)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>50
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_CLEAR'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_ADDRESS_POSTCODE'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(11363926373427895)
        ,p_name=>'set_probation_fields_readonly'
        ,p_event_sequence=>70
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'app_ctx_pkg.get(''SERVICE_ELM'') = ''HMPS'' OR',
            'app_ctx_pkg.get(app_standard_context_pkg.gc_ct_area_est_code) != :P10_OWNING_PROBATION_AREA',
            'OR :P0_READONLY = ''Y'''))
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(11364230338427896)
        ,p_event_id=>wwv_flow_api.id(11363926373427895)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_OFFENDER_MANAGED_IND,P10_PROB_LDU,P10_OFF_MAN_TEAM,P10_OFF_MANAGER'
        ,p_attribute_01=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'setreadonly(this.affectedElements);',
            ''))
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(11373501036666630)
        ,p_event_id=>wwv_flow_api.id(11363926373427895)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_OFFENDER_MANAGED_IND,P10_PROB_LDU,P10_OFF_MAN_TEAM,P10_OFF_MANAGER'
        ,p_attribute_01=>'unsetreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(11371424660578827)
        ,p_name=>'set_prison_fields_readonly'
        ,p_event_sequence=>80
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'app_ctx_pkg.get(''SERVICE_ELM'') = ''NPS'' /* Read only for Probation User */',
            'OR :P0_READONLY = ''Y'''))
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(11371732587578836)
        ,p_event_id=>wwv_flow_api.id(11371424660578827)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PRIS_LDU,P10_OFF_SUP_TEAM,P10_OFF_SUP'
        ,p_attribute_01=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'setreadonly(this.affectedElements);',
            ''))
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(11374225277673607)
        ,p_event_id=>wwv_flow_api.id(11371424660578827)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PRIS_LDU,P10_OFF_SUP_TEAM,P10_OFF_SUP'
        ,p_attribute_01=>'unsetreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(11401724443638542)
        ,p_name=>'non_controling_owner_prison'
        ,p_event_sequence=>90
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'app_ctx_pkg.get(app_standard_context_pkg.gc_ct_area_est_code) =',
            ':P10_OWNING_PRISON_AREA',
            'AND :P0_READONLY = ''N'''))
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(11402008227638543)
        ,p_event_id=>wwv_flow_api.id(11401724443638542)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PRIS_LDU,P10_OFF_SUP_TEAM,P10_OFF_SUP'
        ,p_attribute_01=>'unsetreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(11402530939659364)
        ,p_name=>'non_controling_owner_probation'
        ,p_event_sequence=>100
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'app_ctx_pkg.get(app_standard_context_pkg.gc_ct_area_est_code) =',
            ':P10_OWNING_PROBATION_AREA',
            'AND :P0_READONLY = ''N'''))
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(11402828906659364)
        ,p_event_id=>wwv_flow_api.id(11402530939659364)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_OFFENDER_MANAGED_IND,P10_PROB_LDU,P10_OFF_MAN_TEAM,P10_OFF_MANAGER'
        ,p_attribute_01=>'unsetreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(11457125444104063)
        ,p_name=>'psr_area'
        ,p_event_sequence=>120
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'app_ctx_pkg.get(app_standard_context_pkg.gc_ct_area_est_code) = NVL(:P10_PSR_AREA_CODE,''x'')',
            'AND :P0_READONLY = ''N'''))
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(11457407253104064)
        ,p_event_id=>wwv_flow_api.id(11457125444104063)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PSR_TEAM,P10_PSR_ASSESSOR'
        ,p_attribute_01=>'unsetreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(11460231640153096)
        ,p_event_id=>wwv_flow_api.id(11457125444104063)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PSR_TEAM,P10_PSR_ASSESSOR'
        ,p_attribute_01=>'setreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(11478331938175106)
        ,p_name=>'not_psr_area'
        ,p_event_sequence=>130
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>'app_ctx_pkg.get(app_standard_context_pkg.gc_ct_area_est_code) != :P10_PSR_AREA_CODE OR :P10_PSR_AREA_CODE IS NULL'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(11478623844175109)
        ,p_event_id=>wwv_flow_api.id(11478331938175106)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PSR_TEAM,P10_PSR_ASSESSOR'
        ,p_attribute_01=>'setreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(11615520505390513)
        ,p_name=>'set_other_location_readonly'
        ,p_event_sequence=>140
        ,p_triggering_element_type=>'ITEM'
        ,p_triggering_element=>'P10_PHYSICAL_LOCATION_OTHER'
        ,p_triggering_condition_type=>'JAVASCRIPT_EXPRESSION'
        ,p_triggering_expression=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'document.getElementById(''P10_PHYSICAL_LOCATION_OTHER'').value.toUpperCase() in {DEPORTED:1, COMMUNITY:1} ',
            ''))
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'change'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            ':P0_READONLY = ''N'' AND :P10_PHYSICAL_LOCATION_OTHER IN (''DEPORTED'', ''COMMUNITY'')',
            ''))
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(11615823436390514)
        ,p_event_id=>wwv_flow_api.id(11615520505390513)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PHYSICAL_LOCATION_OTHER'
        ,p_attribute_01=>'setreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(11438902485614822)
        ,p_name=>'prison_user'
        ,p_event_sequence=>150
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'app_ctx_pkg.get(''SERVICE_ELM'') = ''HMPS'' ',
            ''))
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(11439213687614823)
        ,p_event_id=>wwv_flow_api.id(11438902485614822)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_FAMILY_NAME,P10_FORENAME_1,P10_FORENAME_2,P10_MERGE_PNC_NUMBER,P10_FORENAME_3,P10_PHYSICAL_LOCATION,P10_DATE_OF_BIRTH,P10_CMS_PROB_NUMBER,P10_CRO_NUMBER,P10_DECEASED_IND'
        ,p_attribute_01=>'setreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(6263317747600634)
        ,p_name=>'Other Location set to DEPORTED'
        ,p_event_sequence=>160
        ,p_triggering_element_type=>'ITEM'
        ,p_triggering_element=>'P10_PHYSICAL_LOCATION_OTHER'
        ,p_triggering_condition_type=>'JAVASCRIPT_EXPRESSION'
        ,p_triggering_expression=>'$v(''P10_PHYSICAL_LOCATION_OTHER'').toUpperCase() in {DEPORTED:1}'
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'change'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>':P0_LAO_RESTRICTED = ''N'''
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(6263618148600634)
        ,p_event_id=>wwv_flow_api.id(6263317747600634)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_DATE_OF_DEPORTATION'
        ,p_attribute_01=>'$(this.affectedElements).each(function(){apex.item(this).show()})'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(6263820457600635)
        ,p_event_id=>wwv_flow_api.id(6263317747600634)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>20
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_DATE_OF_DEPORTATION'
        ,p_attribute_01=>'$(this.affectedElements).each(function(){apex.item(this).hide()})'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(6264024601600635)
        ,p_event_id=>wwv_flow_api.id(6263317747600634)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>20
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_DECEASED_IND,P10_DATE_OF_DEATH,P10_OTHER_LOCATION_IND'
        ,p_attribute_01=>'setreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(1851530565470640)
        ,p_name=>'no_full_access_set_readonly'
        ,p_event_sequence=>200
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>':P0_READONLY IN (''Y'',''R'')'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(1851828701470643)
        ,p_event_id=>wwv_flow_api.id(1851530565470640)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_DECEASED_IND,P10_OTHER_LOCATION_IND'
        ,p_attribute_01=>'setreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(18486411846520316)
        ,p_name=>'display_other_location'
        ,p_event_sequence=>210
        ,p_triggering_element_type=>'ITEM'
        ,p_triggering_element=>'P10_OTHER_LOCATION_IND'
        ,p_triggering_condition_type=>'JAVASCRIPT_EXPRESSION'
        ,p_triggering_expression=>'$("#P10_OTHER_LOCATION_IND_0").prop("checked")'
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'click'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>':P0_LAO_RESTRICTED = ''N'''
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(18486720617520317)
        ,p_event_id=>wwv_flow_api.id(18486411846520316)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PHYSICAL_LOCATION_OTHER'
        ,p_attribute_01=>'$(this.affectedElements).each(function(){apex.item(this).show()})'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(18486910860520317)
        ,p_event_id=>wwv_flow_api.id(18486411846520316)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>20
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PHYSICAL_LOCATION_OTHER'
        ,p_attribute_01=>'$(this.affectedElements).each(function(){apex.item(this).hide()})'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(18492106866859493)
        ,p_event_id=>wwv_flow_api.id(18486411846520316)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>20
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PHYSICAL_LOCATION'
        ,p_attribute_01=>'$(this.affectedElements).each(function(){apex.item(this).hide()})'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(18487102687520317)
        ,p_event_id=>wwv_flow_api.id(18486411846520316)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>30
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_CLEAR'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PHYSICAL_LOCATION_OTHER'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(18491928206856214)
        ,p_event_id=>wwv_flow_api.id(18486411846520316)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>40
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PHYSICAL_LOCATION'
        ,p_attribute_01=>'$(this.affectedElements).each(function(){apex.item(this).show()})'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(13238330185242834)
        ,p_name=>'display_deceased_date'
        ,p_event_sequence=>220
        ,p_triggering_element_type=>'ITEM'
        ,p_triggering_element=>'P10_DECEASED_IND'
        ,p_triggering_condition_type=>'JAVASCRIPT_EXPRESSION'
        ,p_triggering_expression=>'$("#P10_DECEASED_IND_0").prop("checked")'
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'click'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>':P0_LAO_RESTRICTED = ''N'''
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(13238621650242834)
        ,p_event_id=>wwv_flow_api.id(13238330185242834)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_DATE_OF_DEATH'
        ,p_attribute_01=>'$(this.affectedElements).each(function(){apex.item(this).show()})'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(13238825896242835)
        ,p_event_id=>wwv_flow_api.id(13238330185242834)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>20
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_DATE_OF_DEATH'
        ,p_attribute_01=>'$(this.affectedElements).each(function(){apex.item(this).hide()})'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(1853203787604804)
        ,p_name=>'offender_supervisor_changed'
        ,p_event_sequence=>270
        ,p_triggering_element_type=>'ITEM'
        ,p_triggering_element=>'P10_OFF_SUP'
        ,p_bind_type=>'live'
        ,p_bind_event_type=>'change'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(1853501428604806)
        ,p_event_id=>wwv_flow_api.id(1853203787604804)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_SET_VALUE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_OFF_SUP_POS'
        ,p_attribute_01=>'FUNCTION_BODY'
        ,p_attribute_06=>'RETURN MOD_OFF030_PKG.get_user_position ( p_in_oasys_user_uk => :P10_OFF_SUP);'
        ,p_attribute_07=>'P10_OFF_SUP'
        ,p_attribute_08=>'Y'
        ,p_stop_execution_on_error=>'Y'
        ,p_wait_for_result=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(13394214232023224)
        ,p_event_id=>wwv_flow_api.id(1853203787604804)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>20
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_SET_VALUE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PRISON_EMAIL'
        ,p_attribute_01=>'FUNCTION_BODY'
        ,p_attribute_06=>'RETURN MOD_OFF030_PKG.get_user_email ( p_in_oasys_user_uk => :P10_OFF_SUP);'
        ,p_attribute_08=>'Y'
        ,p_stop_execution_on_error=>'Y'
        ,p_wait_for_result=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(13394422890025761)
        ,p_event_id=>wwv_flow_api.id(1853203787604804)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>30
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_SET_VALUE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PRISON_TEL_NO'
        ,p_attribute_01=>'FUNCTION_BODY'
        ,p_attribute_06=>'RETURN MOD_OFF030_PKG.get_user_phone_number ( p_in_oasys_user_uk => :P10_OFF_SUP);'
        ,p_attribute_08=>'Y'
        ,p_stop_execution_on_error=>'Y'
        ,p_wait_for_result=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(13390027851885236)
        ,p_name=>'offender_manager_changed'
        ,p_event_sequence=>280
        ,p_triggering_element_type=>'ITEM'
        ,p_triggering_element=>'P10_OFF_MANAGER'
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'change'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(13390331677885242)
        ,p_event_id=>wwv_flow_api.id(13390027851885236)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_SET_VALUE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_OFF_MAN_POS'
        ,p_attribute_01=>'FUNCTION_BODY'
        ,p_attribute_06=>'RETURN MOD_OFF030_PKG.get_user_position ( p_in_oasys_user_uk => :P10_OFF_MANAGER);'
        ,p_attribute_07=>'P10_OFF_MANAGER'
        ,p_attribute_08=>'Y'
        ,p_stop_execution_on_error=>'Y'
        ,p_wait_for_result=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(13392131753943106)
        ,p_event_id=>wwv_flow_api.id(13390027851885236)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>20
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_SET_VALUE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PROB_EMAIL'
        ,p_attribute_01=>'FUNCTION_BODY'
        ,p_attribute_06=>'RETURN MOD_OFF030_PKG.get_user_email ( p_in_oasys_user_uk => :P10_OFF_MANAGER);'
        ,p_attribute_07=>'P10_OFF_MANAGER'
        ,p_attribute_08=>'Y'
        ,p_stop_execution_on_error=>'Y'
        ,p_wait_for_result=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(13392307297945573)
        ,p_event_id=>wwv_flow_api.id(13390027851885236)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>30
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_SET_VALUE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PROB_TEL_NO'
        ,p_attribute_01=>'FUNCTION_BODY'
        ,p_attribute_06=>'RETURN MOD_OFF030_PKG.get_user_phone_number ( p_in_oasys_user_uk => :P10_OFF_MANAGER);'
        ,p_attribute_08=>'Y'
        ,p_stop_execution_on_error=>'Y'
        ,p_wait_for_result=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(12055920627455528)
        ,p_name=>'rowHighlight Alias'
        ,p_event_sequence=>290
        ,p_triggering_element_type=>'REGION'
        ,p_triggering_region_id=>wwv_flow_api.id(2837929424403404)
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'apexafterrefresh'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '(:P10_TABSET1 = ''TABSET1_TAB3'')',
            'and',
            '(assessment_utils_pkg.gc_force_crn = FALSE)'))
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(12056221534455530)
        ,p_event_id=>wwv_flow_api.id(12055920627455528)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_attribute_01=>'rowHighlight();'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(12057019719464715)
        ,p_name=>'rowHighlight Assessments'
        ,p_event_sequence=>300
        ,p_triggering_element_type=>'REGION'
        ,p_triggering_region_id=>wwv_flow_api.id(5522906992105444)
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'apexafterrefresh'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_display_when_cond=>'P10_TABSET1'
        ,p_display_when_cond2=>'TABSET1_TAB1'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(12057301694464716)
        ,p_event_id=>wwv_flow_api.id(12057019719464715)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_attribute_01=>'rowHighlight();'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(12059319157474024)
        ,p_name=>'rowHighlight RF|i'
        ,p_event_sequence=>310
        ,p_triggering_element_type=>'REGION'
        ,p_triggering_region_id=>wwv_flow_api.id(6455713139036023)
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'apexafterrefresh'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_display_when_cond=>'P10_TABSET1'
        ,p_display_when_cond2=>'TABSET1_TAB7'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(12059614405474025)
        ,p_event_id=>wwv_flow_api.id(12059319157474024)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_attribute_01=>'rowHighlight();'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(12060112016481384)
        ,p_name=>'rowHighlight INTBDTTO'
        ,p_event_sequence=>320
        ,p_triggering_element_type=>'REGION'
        ,p_triggering_region_id=>wwv_flow_api.id(10826303560893536)
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'apexafterrefresh'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_display_when_cond=>'P10_TABSET1'
        ,p_display_when_cond2=>'TABSET1_TAB8'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(12060431214481385)
        ,p_event_id=>wwv_flow_api.id(12060112016481384)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_attribute_01=>'rowHighlight();'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(2484701200238587)
        ,p_name=>'review_reminder_readonly'
        ,p_event_sequence=>325
        ,p_triggering_condition_type=>'JAVASCRIPT_EXPRESSION'
        ,p_triggering_expression=>'$v(''P10_REVIEW_DATE'').length==0'
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(2485030181238588)
        ,p_event_id=>wwv_flow_api.id(2484701200238587)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_REVIEW_REMINDER_DATE'
        ,p_attribute_01=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'setreadonly(this.affectedElements);',
            ''))
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(12259602574884691)
        ,p_name=>'Process Retained Ind change'
        ,p_event_sequence=>330
        ,p_triggering_element_type=>'ITEM'
        ,p_triggering_element=>'P10_RETAINED_IND'
        ,p_triggering_condition_type=>'EQUALS'
        ,p_triggering_expression=>'Y'
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'click'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>'NOT mod_off030_pkg.p10_disable_data_retention'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(12259918085884701)
        ,p_event_id=>wwv_flow_api.id(12259602574884691)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_RETAINED_REASON'
        ,p_attribute_01=>'unsetreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(12309122107043315)
        ,p_event_id=>wwv_flow_api.id(12259602574884691)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_SET_VALUE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_RETAINED_REASON'
        ,p_attribute_01=>'STATIC_ASSIGNMENT'
        ,p_attribute_02=>' '
        ,p_stop_execution_on_error=>'Y'
        ,p_wait_for_result=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(12260111999884703)
        ,p_event_id=>wwv_flow_api.id(12259602574884691)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>20
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_RETAINED_REASON'
        ,p_attribute_01=>'setreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(12260529239892470)
        ,p_event_id=>wwv_flow_api.id(12259602574884691)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>20
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'JQUERY_SELECTOR'
        ,p_affected_elements=>'input[name=P10_DELETE_OFFENDER]'
        ,p_attribute_01=>'$(this.affectedElements).hide();'
        ,p_stop_execution_on_error=>'Y'
        ,p_da_action_comment=>'KG. 17/03/2011 changed to javascript '
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(12260703051894313)
        ,p_event_id=>wwv_flow_api.id(12259602574884691)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>30
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'JQUERY_SELECTOR'
        ,p_affected_elements=>'input[name=P10_DELETE_OFFENDER]'
        ,p_attribute_01=>'$(this.affectedElements).show();'
        ,p_stop_execution_on_error=>'Y'
        ,p_da_action_comment=>'KG 17/03/2011 changed to javascript'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(12325100822500787)
        ,p_name=>'Disable Retention Details'
        ,p_event_sequence=>340
        ,p_triggering_element_type=>'ITEM'
        ,p_triggering_element=>'P10_RETAINED_REASON'
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'change'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>'mod_off030_pkg.p10_disable_data_retention'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(12325420006500787)
        ,p_event_id=>wwv_flow_api.id(12325100822500787)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_RETAINED_REASON'
        ,p_attribute_01=>'setreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(5446815169056960)
        ,p_name=>'FNC1508_readonly'
        ,p_event_sequence=>345
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>'authorisation_pkg.is_function_authorised(p_ref_function_code => ''FNC1508'') = false'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(5447323426056965)
        ,p_event_id=>wwv_flow_api.id(5446815169056960)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PROB_LDU,P10_OFF_MAN_TEAM,P10_OFF_MANAGER,P10_PRIS_LDU,P10_OFF_SUP_TEAM,P10_OFF_SUP'
        ,p_attribute_01=>'setreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(5881628275607514)
        ,p_name=>'readonly'
        ,p_event_sequence=>355
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_display_when_type=>'SQL_EXPRESSION'
        ,p_display_when_cond=>'nvl(:P0_READONLY,''N'') != ''N'''
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(5881900671607515)
        ,p_event_id=>wwv_flow_api.id(5881628275607514)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_GENDER_ELM,P10_ETHNIC_CATEGORY_ELM'
        ,p_attribute_01=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'setreadonly(this.affectedElements);',
            ''))
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(9418027880015803)
        ,p_name=>'CMS Label'
        ,p_event_sequence=>365
        ,p_bind_type=>'live'
        ,p_bind_event_type=>'ready'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(9428123263609544)
        ,p_event_id=>wwv_flow_api.id(9418027880015803)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>20
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'JAVASCRIPT_EXPRESSION'
        ,p_affected_elements=>'document'
        ,p_attribute_01=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '$("input:button").each(function() {',
            'if ($(this).attr(''value'') == ''Update From CMS'') {',
            '$(this).attr(''value'',''Update from ''+$v(''P10_AREA_CMS''));',
            '}',
            '});'))
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(74400609572054209)
        ,p_name=>'Probation CRN Readonly'
        ,p_event_sequence=>400
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '(app_ctx_pkg.get(''SERVICE_ELM'') = ''NPS'')',
            'and',
            '(assessment_utils_pkg.gc_force_crn)'))
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(74400909267054209)
        ,p_event_id=>wwv_flow_api.id(74400609572054209)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_FAMILY_NAME,P10_FORENAME_1,P10_FORENAME_2,P10_FORENAME_3,P10_DATE_OF_BIRTH,P10_MERGE_PNC_NUMBER,P10_GENDER_ELM,P10_DECIDER_PNC,P10_CMS_PROB_NUMBER,P10_CRO_NUMBER,P10_ADDRESS_LINE_1,P10_ADDRESS_LINE_2,P10_ADDRESS_LINE_3,P10_ADDRESS_LINE_4,P10_ADDR'
        ||'ESS_LINE_5,P10_ADDRESS_LINE_6,P10_ADDRESS_POSTCODE,P10_TELEPHONE_NUMBER'
        ,p_attribute_01=>'setreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(2896246352400137)
        ,p_name=>'Prison NOMS No Readonly'
        ,p_event_sequence=>410
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>'not authorisation_pkg.is_function_authorised(p_ref_function_code=>''EOPNN'')'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(2896652486400138)
        ,p_event_id=>wwv_flow_api.id(2896246352400137)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_CMS_PRIS_NUMBER'
        ,p_attribute_01=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'setreadonly(this.affectedElements);',
            ''))
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(4274010225129222)
        ,p_name=>'show_hide_pcn'
        ,p_event_sequence=>420
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>'app_ctx_pkg.get(''SERVICE_ELM'') = ''HMPS'' AND NOT authorisation_pkg.is_function_authorised(p_ref_function_code=>''EOPPU'')'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(4274476077129223)
        ,p_event_id=>wwv_flow_api.id(4274010225129222)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PNC'
        ,p_attribute_01=>'setreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
end;
/
begin
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(5395266512829016)
        ,p_name=>'Prison Number Readonly'
        ,p_event_sequence=>430
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>'NOT authorisation_pkg.is_function_authorised(p_ref_function_code=>''EOPLN'')'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(5395602103829016)
        ,p_event_id=>wwv_flow_api.id(5395266512829016)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PRISON_NUMBER'
        ,p_attribute_01=>'setreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(5400428816870802)
        ,p_event_id=>wwv_flow_api.id(5395266512829016)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PRISON_NUMBER'
        ,p_attribute_01=>'setreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(5396158712829017)
        ,p_event_id=>wwv_flow_api.id(5395266512829016)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>20
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_DISABLE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_PRISON_NUMBER'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(14037382341857772)
        ,p_name=>'non_controling_owner_prison_OM'
        ,p_event_sequence=>440
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'ready'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_display_when_cond=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'app_ctx_pkg.get(app_standard_context_pkg.gc_ct_area_est_code) =',
            ':P10_OWNING_PRISON_AREA',
            'AND :P0_READONLY = ''N''',
            'AND :P10_OWNING_PROBATION_AREA IS NOT NULL',
            'AND authorisation_pkg.is_function_authorised(p_ref_function_code=>''FNC1507'')'))
        ,p_da_event_comment=>'Only Allow Prison user to enable / disable OM flag if there is a probation owner.'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(14037729743857773)
        ,p_event_id=>wwv_flow_api.id(14037382341857772)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P10_OFFENDER_MANAGED_IND'
        ,p_attribute_01=>'unsetreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(15770417411389933)
        ,p_process_sequence=>10
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_SESSION_STATE'
        ,p_process_name=>'P10_OSACV_CLEAR_ASS_WARN_MSG'
        ,p_attribute_01=>'CLEAR_CACHE_FOR_ITEMS'
        ,p_attribute_03=>'P10_WARNING_MSG_TEXT'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(12869116739824516)
        ,p_process_sequence=>20
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P10_OAS_SAVE_OFFENDER'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '',
            '  -- 1. save offender',
            '  -- ',
            '  mod_OFF030_pkg.p10_osa_save_offender;',
            '  -- ',
            '  --',
            '  EXCEPTION WHEN OTHERS',
            '  THEN',
            '    ELOG_API.INS;',
            '  RAISE;',
            '  --',
            'END;'))
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(6991215752989344)
        ,p_process_sequence=>70
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P10_OSA_LOCK_ASSESSMENT_1'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '',
            '   -- The :REQUEST will look something like ''LOCKREQ_OASYS_SET_PK=12345'' ',
            '   -- therefore strip out the number from the request. ',
            '   :P500_OASYS_SET_PK := TO_NUMBER( SUBSTR(:REQUEST,22));',
            '   app_ctx_pkg.set(app_standard_context_pkg.gc_oasys_set_pk,TO_NUMBER( SUBSTR(:REQUEST,22)));',
            '',
            '  EXCEPTION WHEN OTHERS',
            '  THEN',
            '    ELOG_API.INS;',
            '  RAISE;',
            '  --',
            'END;'))
        ,p_process_when=>'RETURN (:REQUEST LIKE ''LOCKREQ_OASYS_SET_PK%'');'
        ,p_process_when_type=>'FUNCTION_BODY'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(7325316299891083)
        ,p_process_sequence=>90
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P10_OSA_LOCK_ASSESSMENT_2'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '    -- lock assessment',
            '',
            '  mod_off030_pkg.lock_incomplete',
            '  ( p_offender_pk   => :P10_OFFENDER_PK',
            '  , p_oasys_set_pk  => TO_NUMBER(SUBSTR(:REQUEST,18))',
            '  ) ;',
            '',
            '   EXCEPTION WHEN OTHERS ',
            '   THEN',
            '     elog_api.ins;',
            '     RAISE;    ',
            'END;'))
        ,p_process_when=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'RETURN (:REQUEST LIKE ''COUNTERSIGN_LOCK%'');',
            '',
            ''))
        ,p_process_when_type=>'FUNCTION_BODY'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(8230430839002205)
        ,p_process_sequence=>100
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P10_OSA_OFFENDER_MANAGED'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '',
            '    -- Set the controlling owner.',
            '    mod_OFF030_pkg.p10_CHANAGE_OFFENDER_MANAGED;',
            '',
            ' EXCEPTION WHEN OTHERS ',
            ' THEN',
            '   elog_api.ins;',
            '   RAISE;    ',
            'END;'))
        ,p_process_when=>':REQUEST = ''P10_OFFENDER_MANAGED_IND'' AND :P0_READONLY = ''N'''
        ,p_process_when_type=>'PLSQL_EXPRESSION'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(11194805991937148)
        ,p_process_sequence=>110
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_SESSION_STATE'
        ,p_process_name=>'P10_0SA_CLEAR_CACHE_PAGE50'
        ,p_attribute_01=>'CLEAR_CACHE_FOR_PAGES'
        ,p_attribute_04=>'50'
        ,p_process_when=>'CONFIRMENTER_INTBDTTO'
        ,p_process_when_type=>'REQUEST_EQUALS_CONDITION'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(11909219246423307)
        ,p_process_sequence=>120
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P10_OSA_PROCESS_REQUEST'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '  app_navigation_pkg.process_request(:REQUEST);',
            'end;'))
        ,p_process_when=>':REQUEST LIKE ''PAGE_50%'' OR :REQUEST LIKE ''PAGE_60%'' OR :REQUEST LIKE ''PAGE_20%'''
        ,p_process_when_type=>'PLSQL_EXPRESSION'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(5500127912146287)
        ,p_process_sequence=>130
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'reset_init_flag'
        ,p_process_sql_clob=>':P10_INIT := ''N'';'
        ,p_process_when=>':REQUEST LIKE ''%MOD_ASS010%'' OR :REQUEST LIKE ''%PAGE_60%'''
        ,p_process_when_type=>'PLSQL_EXPRESSION'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(7865016533714782)
        ,p_process_sequence=>150
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_SESSION_STATE'
        ,p_process_name=>'clear item cache'
        ,p_attribute_01=>'CLEAR_CACHE_FOR_ITEMS'
        ,p_attribute_03=>'P10_INIT'
        ,p_process_when=>'P10_BT_CREATE_ASSESSMENT'
        ,p_process_when_type=>'REQUEST_EQUALS_CONDITION'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(7870821063943258)
        ,p_process_sequence=>160
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P10_BRANCH_PROCESSING'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '',
            '   mod_off030_pkg.p10_osacv_process;',
            '',
            '   EXCEPTION WHEN OTHERS',
            '   THEN',
            '   ELOG_API.INS;',
            '   RAISE;',
            '',
            'END;'))
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(15770223168382157)
        ,p_process_sequence=>170
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P10_OSACV_SET_CREATE_ASS_WARN'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '   MOD_OFF030_PKG.p10_osacv_set_create_ass_warn;',
            'END;'))
        ,p_process_when=>'RETURN (:REQUEST = ''P10_BT_CREATE_ASSESSMENT'');'
        ,p_process_when_type=>'FUNCTION_BODY'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(5760526985635304)
        ,p_process_sequence=>10
        ,p_process_point=>'BEFORE_HEADER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P10_OLBH_RBAC'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '  mod_off030_pkg.p10_olbh_rbac;',
            'END;'))
        ,p_process_when=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            'RETURN (app_ctx_pkg.get (app_standard_context_pkg.gc_offender_pk) IS NOT NULL);',
            'END;'))
        ,p_process_when_type=>'FUNCTION_BODY'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(2840507363529467)
        ,p_process_sequence=>20
        ,p_process_point=>'BEFORE_HEADER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P10_OLBH_GET_OFFENDER'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '  --',
            '  mod_OFF030_pkg.P10_OLBH_GET_OFFENDER_MAIN;',
            '  --',
            'EXCEPTION WHEN OTHERS',
            '  THEN',
            '  ELOG_API.INS;',
            '  RAISE;',
            '  --',
            'END;'))
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(2781732600851798)
        ,p_process_sequence=>30
        ,p_process_point=>'BEFORE_HEADER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P10_OLBH_SETUP_TABS'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'begin',
            '       ',
            '    -- SETUP TITLES',
            '    app_ui_components_pkg.set_browser_title(p_text => ''Offender'');',
            '    app_ui_components_pkg.set_page_title(p_text => ''Offender Details'');',
            '    ',
            '    -- SETUP TABS',
            '    mod_OFF030_pkg.p10_setup_tabs;',
            '',
            'EXCEPTION WHEN OTHERS',
            'THEN',
            '   ELOG_API.INS;',
            '   RAISE;',
            '      --',
            'end;'))
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(15770005375367517)
        ,p_process_sequence=>170
        ,p_process_point=>'BEFORE_HEADER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P10_OLBH_DISP_CREATE_ASS_WARN'
        ,p_process_sql_clob=>'null;'
        ,p_process_when=>'P10_WARNING_MSG_TEXT'
        ,p_process_when_type=>'ITEM_IS_NOT_NULL'
        ,p_process_success_message=>'&P10_WARNING_MSG_TEXT.'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(14863026375744577)
        ,p_process_sequence=>180
        ,p_process_point=>'BEFORE_HEADER'
        ,p_process_type=>'NATIVE_RESET_PAGINATION'
        ,p_process_name=>'Reset Pagination'
        ,p_process_sql_clob=>'reset_pagination'
        ,p_attribute_01=>'THIS_PAGE'
        );
end;
/
prompt --application/pages/page_00020
begin
    wwv_flow_api.create_page(
            p_id=>20
        ,p_user_interface_id=>wwv_flow_api.id(74312104640072)
        ,p_name=>'ADD_NEW_ALIAS'
        ,p_page_mode=>'NORMAL'
        ,p_step_title=>'Add or Edit Alias'
        ,p_step_sub_title=>'OFFENDER_ALIAS'
        ,p_step_sub_title_type=>'TEXT_WITH_SUBSTITUTIONS'
        ,p_first_item=>'AUTO_FIRST_ITEM'
        ,p_autocomplete_on_off=>'OFF'
        ,p_group_id=>wwv_flow_api.id(6767457294623073)
        ,p_html_page_header=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '<script type="text/javascript">',
            '  function deleteConfirm(msg)',
            '  {',
            '  var confDel = msg;',
            '  if(confDel ==null)',
            '    confDel= confirm("Would you like to perform this delete action?");',
            '  else',
            '    confDel= confirm(msg);',
            '  ',
            '  if (confDel== true)',
            '    &AI_NAV_SYS.(''DELETE'');',
            '     }',
            '</script>',
            ''))
        ,p_step_template=>wwv_flow_api.id(20789413245320561)
        ,p_page_template_options=>'#DEFAULT#'
        ,p_required_role=>wwv_flow_api.id(5052631587350853)
        ,p_dialog_chained=>'Y'
        ,p_overwrite_navigation_list=>'N'
        ,p_nav_list_template_options=>'#DEFAULT#'
        ,p_page_is_public_y_n=>'N'
        ,p_protection_level=>'D'
        ,p_cache_mode=>'NOCACHE'
        ,p_cache_timeout_seconds=>21600
        ,p_help_text=>' '
        ,p_page_comment=>'Auto generated page'
        ,p_last_updated_by=>'ZIPPYB'
        ,p_last_upd_yyyymmddhh24miss=>'20110318153737'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(3501121876889948)
        ,p_plug_name=>'Add New Alias'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(20790622026320563)
        ,p_plug_display_sequence=>999
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(8245318300093431)
        ,p_button_sequence=>10
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'DELETE'
        ,p_button_action=>'REDIRECT_URL'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Delete'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_button_redirect_url=>'javascript:confirmDelete(''Are you sure you want to delete this alias?'')'
        ,p_button_execute_validations=>'N'
        ,p_button_condition=>'P20_OFFENDER_ALIAS_PK'
        ,p_button_condition_type=>'ITEM_IS_NOT_NULL'
        ,p_grid_new_grid=>false
        ,p_security_scheme=>wwv_flow_api.id(11371114710319837)
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(3504705646913648)
        ,p_button_sequence=>20
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'SAVE'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790204340320563)
        ,p_button_image_alt=>'Save'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(3504324908909747)
        ,p_button_sequence=>30
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'CLOSE'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790303721320563)
        ,p_button_image_alt=>'Close'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(4899428678561736)
        ,p_branch_action=>'10'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>10
        ,p_branch_condition_type=>'REQUEST_IN_CONDITION'
        ,p_branch_condition=>'CLOSE,DELETE,Delete'
        ,p_branch_comment=>'Created 06-APR-2010 10:39 by ZIPPYMICHAEL'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(7895014061086122)
        ,p_branch_action=>'20'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>99999
        ,p_branch_comment=>'Created 28-SEP-2010 17:07 by ZIPPYMICHAEL'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3501510045889961)
        ,p_name=>'P20_OFFENDER_ALIAS_PK'
        ,p_item_sequence=>10
        ,p_item_plug_id=>wwv_flow_api.id(3501121876889948)
        ,p_prompt=>'Offender Alias Pk'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>22
        ,p_cMaxlength=>22
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3501703889889962)
        ,p_name=>'P20_ALIAS_DATE_OF_BIRTH'
        ,p_item_sequence=>4
        ,p_item_plug_id=>wwv_flow_api.id(3501121876889948)
        ,p_prompt=>'Date Of Birth'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>15
        ,p_cMaxlength=>15
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" data-dateitemtype="date"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_03=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3501808122889962)
        ,p_name=>'P20_ALIAS_FAMILY_NAME'
        ,p_item_sequence=>1
        ,p_item_plug_id=>wwv_flow_api.id(3501121876889948)
        ,p_prompt=>'Family Name'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>32
        ,p_cMaxlength=>32
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_03=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3501900517889962)
        ,p_name=>'P20_NAME_SOUNDSLIKE'
        ,p_item_sequence=>50
        ,p_item_plug_id=>wwv_flow_api.id(3501121876889948)
        ,p_prompt=>'Soundslike'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>30
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3502000158889962)
        ,p_name=>'P20_ALIAS_FORENAME_1'
        ,p_item_sequence=>2
        ,p_item_plug_id=>wwv_flow_api.id(3501121876889948)
        ,p_prompt=>'Forename 1'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>32
        ,p_cMaxlength=>32
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_03=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3502104126889962)
        ,p_name=>'P20_ALIAS_FORENAME_2'
        ,p_item_sequence=>3
        ,p_item_plug_id=>wwv_flow_api.id(3501121876889948)
        ,p_prompt=>'Other Forename(s)'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>32
        ,p_cMaxlength=>32
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_03=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3502211113889962)
        ,p_name=>'P20_OFFENDER_PK'
        ,p_item_sequence=>80
        ,p_item_plug_id=>wwv_flow_api.id(3501121876889948)
        ,p_prompt=>'Offender Pk'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>22
        ,p_cMaxlength=>22
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(3502314735889962)
        ,p_name=>'P20_CHECKSUM'
        ,p_item_sequence=>90
        ,p_item_plug_id=>wwv_flow_api.id(3501121876889948)
        ,p_prompt=>'Checksum'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>100
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_validation(
            p_id=>wwv_flow_api.id(4914722453685895)
        ,p_validation_name=>'P20_OSA_VALIDATE_OFFENDER_ALIAS'
        ,p_validation_sequence=>10
        ,p_validation=>'RETURN MOD_OFF030_PKG.P20_OSA_VALIDATE_ALIAS;'
        ,p_validation_type=>'FUNC_BODY_RETURNING_ERR_TEXT'
        ,p_error_message=>'Error'
        ,p_always_execute=>'N'
        ,p_when_button_pressed=>wwv_flow_api.id(3504705646913648)
        ,p_error_display_location=>'INLINE_IN_NOTIFICATION'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(4909504184961463)
        ,p_process_sequence=>40
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P10_OAS_SAVE_ALIAS'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'clog_api.ins(p_text=>''SAVE Alias 1.0'');',
            '',
            'clog_api.ins(p_text=>''offender_pk=''||:P20_OFFENDER_PK);',
            '',
            'mod_OFF030_pkg.p20_osa_save_alias;',
            '',
            'clog_api.ins(p_text=>''SAVE Alias 2.0'');'))
        ,p_process_when=>'SAVE'
        ,p_process_when_type=>'REQUEST_EQUALS_CONDITION'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(4915418522750999)
        ,p_process_sequence=>50
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P20_OAS_DELETE_ALIAS'
        ,p_process_sql_clob=>'mod_OFF030_pkg.P20_OSA_DELETE_ALIAS;'
        ,p_process_when=>'Delete'
        ,p_process_when_type=>'REQUEST_EQUALS_CONDITION'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(4914931934698089)
        ,p_process_sequence=>50
        ,p_process_point=>'BEFORE_HEADER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'GET_OFFENDER_ALIAS'
        ,p_process_sql_clob=>'mod_OFF030_pkg.P20_OLBH_GET_ALIAS;'
        );
end;
/
prompt --application/pages/page_00030
begin
    wwv_flow_api.create_page(
            p_id=>30
        ,p_user_interface_id=>wwv_flow_api.id(74312104640072)
        ,p_name=>'EDIT ADDITIONAL TEAMS'
        ,p_page_mode=>'NORMAL'
        ,p_step_title=>'&P30_PAGE_TITLE.'
        ,p_step_sub_title_type=>'TEXT_WITH_SUBSTITUTIONS'
        ,p_first_item=>'NO_FIRST_ITEM'
        ,p_autocomplete_on_off=>'OFF'
        ,p_group_id=>wwv_flow_api.id(6767457294623073)
        ,p_page_template_options=>'#DEFAULT#'
        ,p_required_role=>wwv_flow_api.id(5052631587350853)
        ,p_dialog_chained=>'Y'
        ,p_overwrite_navigation_list=>'N'
        ,p_nav_list_template_options=>'#DEFAULT#'
        ,p_page_is_public_y_n=>'N'
        ,p_protection_level=>'U'
        ,p_cache_mode=>'NOCACHE'
        ,p_cache_timeout_seconds=>21600
        ,p_last_updated_by=>'ADMIN'
        ,p_last_upd_yyyymmddhh24miss=>'20160219141852'
        );
    wwv_flow_api.create_report_region(
            p_id=>wwv_flow_api.id(5311519432948540)
        ,p_name=>'ADDITIONAL_TEAMS'
        ,p_template=>wwv_flow_api.id(20790622026320563)
        ,p_display_sequence=>2
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_new_grid_row=>false
        ,p_new_grid_column=>false
        ,p_display_column=>1
        ,p_display_point=>'BODY_3'
        ,p_source=>'return /* select 1 from dual */ mod_off030_pkg.p30_additional_teams_query;'
        ,p_source_type=>'NATIVE_FNC_REPORT'
        ,p_ajax_enabled=>'Y'
        ,p_fixed_header=>'NONE'
        ,p_query_row_template=>wwv_flow_api.id(20794602676320568)
        ,p_query_num_rows=>15
        ,p_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_query_show_nulls_as=>' - '
        ,p_query_break_cols=>'0'
        ,p_query_no_data_found=>'no data found'
        ,p_query_num_rows_type=>'SEARCH_ENGINE'
        ,p_query_row_count_max=>500
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_break_type_flag=>'DEFAULT_BREAK_FORMATTING'
        ,p_csv_output=>'N'
        ,p_prn_output=>'N'
        ,p_sort_null=>'F'
        ,p_query_asc_image_attr=>'width="13" height="12" '
        ,p_query_desc_image_attr=>'width="13" height="12" '
        ,p_plug_query_strip_html=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5311820448948549)
        ,p_query_column_id=>1
        ,p_column_alias=>'TEAM_UK'
        ,p_column_display_sequence=>9
        ,p_column_heading=>'Delete'
        ,p_heading_alignment=>'LEFT'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(16004629734621059)
        ,p_query_column_id=>2
        ,p_column_alias=>'TEAM_NAME'
        ,p_column_display_sequence=>11
        ,p_column_heading=>'Team Name'
        ,p_heading_alignment=>'LEFT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(16004702125621059)
        ,p_query_column_id=>3
        ,p_column_alias=>'DIVISION_NAME'
        ,p_column_display_sequence=>10
        ,p_column_heading=>'Division Name'
        ,p_heading_alignment=>'LEFT'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5445116154529243)
        ,p_query_column_id=>4
        ,p_column_alias=>'OFFENDER_TEAM_PK'
        ,p_column_display_sequence=>1
        ,p_column_heading=>'Offender Team Pk'
        ,p_heading_alignment=>'LEFT'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5312216031948551)
        ,p_query_column_id=>5
        ,p_column_alias=>'OFFENDER_PK'
        ,p_column_display_sequence=>7
        ,p_column_heading=>'Offender Pk'
        ,p_heading_alignment=>'LEFT'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5312303916948551)
        ,p_query_column_id=>6
        ,p_column_alias=>'TEAM_ACCESS_TYPE_ELM'
        ,p_column_display_sequence=>6
        ,p_column_heading=>'Access Type'
        ,p_heading_alignment=>'LEFT'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5312801817971765)
        ,p_query_column_id=>7
        ,p_column_alias=>'CT_AREA_EST_CODE'
        ,p_column_display_sequence=>3
        ,p_column_heading=>'&P30_AREA_TITLE.'
        ,p_heading_alignment=>'LEFT'
        ,p_default_sort_column_sequence=>1
        ,p_disable_sort_column=>'N'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(13793729711759142)
        ,p_query_column_id=>8
        ,p_column_alias=>'AREA_EST'
        ,p_column_display_sequence=>2
        ,p_column_heading=>'&P30_AREA_TITLE.'
        ,p_heading_alignment=>'LEFT'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5312902967971765)
        ,p_query_column_id=>9
        ,p_column_alias=>'START_DATE'
        ,p_column_display_sequence=>4
        ,p_column_heading=>'start date'
        ,p_heading_alignment=>'LEFT'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5313013697971765)
        ,p_query_column_id=>10
        ,p_column_alias=>'END_DATE'
        ,p_column_display_sequence=>5
        ,p_column_heading=>'End Date'
        ,p_heading_alignment=>'LEFT'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5313908968040054)
        ,p_query_column_id=>11
        ,p_column_alias=>'REF_ELEMENT_DESC'
        ,p_column_display_sequence=>8
        ,p_column_heading=>'Access Type'
        ,p_heading_alignment=>'LEFT'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(5379011737741052)
        ,p_query_column_id=>12
        ,p_column_alias=>'DEL'
        ,p_column_display_sequence=>12
        ,p_column_alignment=>'RIGHT'
        ,p_heading_alignment=>'LEFT'
        ,p_display_as=>'WITHOUT_MODIFICATION'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(5373413123391396)
        ,p_plug_name=>'Additional Teams'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(20790622026320563)
        ,p_plug_display_sequence=>1
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_plug_display_condition_type=>'PLSQL_EXPRESSION'
        ,p_plug_display_when_condition=>':P0_ACCESS_MODE = offenders_pkg.gc_v_full_access'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(5389330250983019)
        ,p_button_sequence=>46
        ,p_button_plug_id=>wwv_flow_api.id(5373413123391396)
        ,p_button_name=>'P30_ADD_TEAM'
        ,p_button_static_id=>'P30_ADD_TEAM'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Add Team'
        ,p_button_position=>'BODY'
        ,p_button_alignment=>'LEFT'
        ,p_request_source=>'ADD_TEAM'
        ,p_request_source_type=>'STATIC'
        ,p_grid_new_grid=>false
        ,p_grid_new_row=>'N'
        ,p_grid_new_column=>'Y'
        ,p_grid_column_span=>50
        ,p_grid_row_span=>1
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(5308220761844832)
        ,p_button_sequence=>20
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'SAVE'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790204340320563)
        ,p_button_image_alt=>'Save'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(5308426648846464)
        ,p_button_sequence=>30
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'CLOSE'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790303721320563)
        ,p_button_image_alt=>'Close'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(5308622970854869)
        ,p_branch_action=>'10'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_when_button_id=>wwv_flow_api.id(5308426648846464)
        ,p_branch_sequence=>10
        ,p_branch_condition_type=>'REQUEST_IN_CONDITION'
        ,p_branch_condition=>'CLOSE,DELETE'
        ,p_branch_comment=>'Created 06-APR-2010 10:39 by ZIPPYMICHAEL'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(7923528680413589)
        ,p_branch_action=>'30'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>9999
        ,p_branch_comment=>'Created 29-SEP-2010 10:42 by ZIPPYMICHAEL'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(5372719179355290)
        ,p_name=>'P30_ADDITIONAL_TEAM'
        ,p_item_sequence=>42
        ,p_item_plug_id=>wwv_flow_api.id(5373413123391396)
        ,p_prompt=>'Select Team'
        ,p_display_as=>'NATIVE_SELECT_LIST'
        ,p_lov=>'return mod_off030_pkg.p30_lov_additional_team;'
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select A Team --'
        ,p_lov_null_value=>'0'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'style="width:300px;"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NONE'
        ,p_attribute_02=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(5390301338012502)
        ,p_name=>'P30_DIVISION'
        ,p_item_sequence=>41
        ,p_item_plug_id=>wwv_flow_api.id(5373413123391396)
        ,p_prompt=>'Select LDU'
        ,p_display_as=>'NATIVE_SELECT_LIST'
        ,p_lov=>'return mod_off030_pkg.p30_lov_division;'
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>'-- Select LDU --'
        ,p_lov_null_value=>'0'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'style="width:300px;"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'SUBMIT'
        ,p_attribute_03=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(5395808816997265)
        ,p_name=>'P30_OFFENDER_AREA'
        ,p_item_sequence=>43
        ,p_item_plug_id=>wwv_flow_api.id(5373413123391396)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(5401401675004687)
        ,p_name=>'P30_OFFENDER_PK'
        ,p_item_sequence=>44
        ,p_item_plug_id=>wwv_flow_api.id(5373413123391396)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(5401711156016895)
        ,p_name=>'P30_TEAM_ACCESS_TYPE_ELM'
        ,p_item_sequence=>45
        ,p_item_plug_id=>wwv_flow_api.id(5373413123391396)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(5466623154136855)
        ,p_name=>'P30_PAGE_TITLE'
        ,p_item_sequence=>56
        ,p_item_plug_id=>wwv_flow_api.id(5373413123391396)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(5466830426138977)
        ,p_name=>'P30_AREA_TITLE'
        ,p_item_sequence=>66
        ,p_item_plug_id=>wwv_flow_api.id(5373413123391396)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_validation(
            p_id=>wwv_flow_api.id(16002828817554482)
        ,p_validation_name=>'p30_osa_validate_ldu_team'
        ,p_validation_sequence=>10
        ,p_validation=>'RETURN mod_off030_pkg.p30_osa_validate_ldu_team;'
        ,p_validation_type=>'FUNC_BODY_RETURNING_ERR_TEXT'
        ,p_error_message=>'Error.'
        ,p_always_execute=>'N'
        ,p_when_button_pressed=>wwv_flow_api.id(5389330250983019)
        ,p_error_display_location=>'INLINE_IN_NOTIFICATION'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(5373815678401570)
        ,p_process_sequence=>10
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P30_osa_save_team'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '',
            '',
            'clog_api.ins (p_source => ''MICHAEL'',p_text=>''1. Save Additional Team '');                        ',
            '',
            '',
            'clog_api.ins (p_source => ''MICHAEL'',p_text=>'':P30_OFFENDER_PK=''||:P30_OFFENDER_PK);                        ',
            'clog_api.ins (p_source => ''MICHAEL'',p_text=>'':P30_ADDITIONAL_TEAM=''||:P30_ADDITIONAL_TEAM);                        ',
            'clog_api.ins (p_source => ''MICHAEL'',p_text=>'':P30_TEAM_ACCESS_TYPE_ELM=''||:P30_TEAM_ACCESS_TYPE_ELM);                        ',
            'clog_api.ins (p_source => ''MICHAEL'',p_text=>'':P30_OFFENDER_AREA=''||:P30_OFFENDER_AREA);                        ',
            '',
            '-- 1. Save Additional Team',
            'IF :P30_ADDITIONAL_TEAM IS NOT NULL',
            'THEN',
            '  mod_off030_pkg.p10_osa_save_add_team',
            '  ( p_in_team_uk         => :P30_ADDITIONAL_TEAM',
            '  , p_in_access_type_elm => :P30_TEAM_ACCESS_TYPE_ELM',
            '  , p_in_access_type_cat => ''TEAM_ACCESS_TYPE''',
            '  , p_in_ct_area_est_code => :P30_OFFENDER_AREA',
            '  ) ;',
            'END IF;',
            '',
            '',
            'END;'))
        ,p_process_when_button_id=>wwv_flow_api.id(5389330250983019)
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(5421511736158950)
        ,p_process_sequence=>20
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P30_osa_delete'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            '    mod_off030_pkg.p10_osa_delete_off_team',
            '    ( p_in_request => :REQUEST );'))
        ,p_process_when=>'RETURN (:REQUEST like ''DELETE:%'');'
        ,p_process_when_type=>'FUNCTION_BODY'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(5548727307606333)
        ,p_process_sequence=>30
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'p30_osa_refresh_offender'
        ,p_process_sql_clob=>'mod_OFF030_pkg.p10_olbh_get_offender;'
        ,p_process_when_button_id=>wwv_flow_api.id(5308426648846464)
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(5468806540207799)
        ,p_process_sequence=>30
        ,p_process_point=>'BEFORE_HEADER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P30_OLBH_SET_PAGE_TITLE'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'app_ui_components_pkg.set_page_title',
            '( p_text => :P30_PAGE_TITLE );'))
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(3845103308756432)
        ,p_process_sequence=>40
        ,p_process_point=>'BEFORE_HEADER'
        ,p_process_type=>'NATIVE_RESET_PAGINATION'
        ,p_process_name=>'Reset Pagination'
        ,p_process_sql_clob=>'reset_pagination'
        ,p_attribute_01=>'THIS_PAGE'
        );
end;
/
prompt --application/pages/page_00050
begin
    wwv_flow_api.create_page(
            p_id=>50
        ,p_user_interface_id=>wwv_flow_api.id(74312104640072)
        ,p_name=>'OASYS_NOT_DISCLOSED_SECT'
        ,p_page_mode=>'NORMAL'
        ,p_step_title=>'Information not to be disclosed to the Offender'
        ,p_step_sub_title=>'OASYS_NOT_DISCLOSED_SECT'
        ,p_step_sub_title_type=>'TEXT_WITH_SUBSTITUTIONS'
        ,p_first_item=>'AUTO_FIRST_ITEM'
        ,p_autocomplete_on_off=>'OFF'
        ,p_group_id=>wwv_flow_api.id(6767457294623073)
        ,p_javascript_code=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'function appdosubmitconfirm (pRequest) {',
            '',
            '// if it wasn''t the print button that was pressed then just continue',
            ' if ( pRequest.indexOf(''P50_BT_PRINT'') == -1 ) {',
            '    return true;',
            ' }  ',
            '',
            ' return (confirm("You should not print and disclose this INTBDTTO to the offender or the offender''s representatives unless you are sure that it is appropriate to do so.  Providing this could, in some cases, put others at risk (eg. the victim).  Pleas'
                ||'e take particular care with this section of the printout."))',
            '',
            'return true; ',
            '}',
            ''))
        ,p_step_template=>wwv_flow_api.id(20789413245320561)
        ,p_page_template_options=>'#DEFAULT#'
        ,p_required_role=>wwv_flow_api.id(5052631587350853)
        ,p_dialog_chained=>'Y'
        ,p_overwrite_navigation_list=>'N'
        ,p_nav_list_template_options=>'#DEFAULT#'
        ,p_page_is_public_y_n=>'N'
        ,p_protection_level=>'D'
        ,p_cache_mode=>'NOCACHE'
        ,p_cache_timeout_seconds=>21600
        ,p_help_text=>' '
        ,p_page_comment=>'Auto generated page'
        ,p_last_updated_by=>'ZIPPYMICHAEL'
        ,p_last_upd_yyyymmddhh24miss=>'20110503115340'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(10852931457570180)
        ,p_plug_name=>'Information not to be disclosed to the Offender'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_display_sequence=>999
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_plug_header=>'You should not disclose this information to the offender or the offender''s representative unless you are sure that it is appropriate to do so. Providing this could, in some cases, put others at risk (e.g. the victim).'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(10857725419107786)
        ,p_button_sequence=>10
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P50_BT_PRINT'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Print'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_button_condition=>'intbdtto_pkg.p50_display_del_button'
        ,p_button_condition_type=>'PLSQL_EXPRESSION'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(2674301305922595)
        ,p_button_sequence=>15
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'CONFIRM_DELETE'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Confirm Delete'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_button_condition=>'RETURN intbdtto_pkg.p50_display_conf_del_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        ,p_security_scheme=>wwv_flow_api.id(1420103025508904)
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(10858112044113395)
        ,p_button_sequence=>20
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'DELETE'
        ,p_button_action=>'REDIRECT_URL'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Delete'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_button_redirect_url=>'javascript:appevent(event, ''DELETE'', ''Are you sure you wish to delete this INTBDTTO entry? In order to complete the deletion you must enter a reason for deletion and click on `Confirm Delete`.'');'||wwv_flow.LF||
                                ''||wwv_flow.LF||
                                ''
        ,p_button_condition=>'RETURN intbdtto_pkg.p50_display_del_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        ,p_security_scheme=>wwv_flow_api.id(1420103025508904)
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(10946111778407136)
        ,p_button_sequence=>30
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'SUPERSEDE'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Mark As Superseded'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_button_condition=>'RETURN intbdtto_pkg.p50_display_SUPERSEDE_button;'
        ,p_button_condition2=>'Y'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        ,p_security_scheme=>wwv_flow_api.id(11371114710319837)
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(10934503301692073)
        ,p_button_sequence=>40
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P50_BT_SAVE'
        ,p_button_action=>'REDIRECT_URL'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790204340320563)
        ,p_button_image_alt=>'Save'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_button_redirect_url=>'javascript:&AI_NAV_SYS.(''SAVE'');'
        ,p_button_condition=>'RETURN intbdtto_pkg.p50_display_save_button;'
        ,p_button_condition_type=>'FUNCTION_BODY'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(10934710227693997)
        ,p_button_sequence=>50
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P50_BT_CLOSE'
        ,p_button_action=>'REDIRECT_PAGE'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790303721320563)
        ,p_button_image_alt=>'Close'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_button_redirect_url=>'f?p=&APP_ID.:10:&SESSION.::&DEBUG.:::'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(13365006689870556)
        ,p_branch_action=>'10'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>15
        ,p_branch_condition_type=>'PLSQL_EXPRESSION'
        ,p_branch_condition=>':REQUEST IN (''CLOSE'',''CONFIRM_DELETE'')'
        ,p_branch_comment=>'Created 23-MAR-2011 10:58 by KGAMBLE'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(11251425601984022)
        ,p_branch_action=>'return app_navigation_pkg.navurl(''MOD_PRN030::0,100:P0_PRINT_TYPE,P0_OASYS_NOT_DISCLOSED_SECT_PK:INTBDTTO,&P50_OASYS_NDS_PK.'');'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_FUNCTION_RETURNING_URL'
        ,p_branch_when_button_id=>wwv_flow_api.id(10857725419107786)
        ,p_branch_sequence=>30
        ,p_save_state_before_branch_yn=>'Y'
        ,p_branch_comment=>'Created 24-FEB-2011 14:50 by ZIPPYROB'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(11248929270899922)
        ,p_branch_action=>'50'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>35
        ,p_branch_comment=>'Created 24-FEB-2011 14:36 by ZIPPYROB'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1743914945500790)
        ,p_name=>'P50_DELETE_YN'
        ,p_item_sequence=>213
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'Delete YN'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1744101224506348)
        ,p_name=>'P50_DELETION_REASON'
        ,p_item_sequence=>223
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'Deletion Reason&nbsp&nbsp'
        ,p_display_as=>'NATIVE_TEXTAREA'
        ,p_cSize=>100
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when=>'P50_DELETE_YN'
        ,p_display_when2=>'Y'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_read_only_when=>'P50_OBSOLETED_DATE'
        ,p_read_only_disp_attr=>'class="readOnlyFieldLeft" '
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_03=>'N'
        ,p_attribute_04=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1747017734652998)
        ,p_name=>'P50_DELETED_DATE'
        ,p_item_sequence=>233
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'Deleted Date'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>10
        ,p_cMaxlength=>10
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'data-dateitemtype="date" autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_display_when=>'P50_SUPERSEDE_YN'
        ,p_display_when2=>'Y'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(10853332463570194)
        ,p_name=>'P50_OASYS_NDS_PK'
        ,p_item_sequence=>188
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'Oasys Not Disclosed Sect Pk'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>22
        ,p_cMaxlength=>22
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(10853420644570198)
        ,p_name=>'P50_INFO_WITHHELD_OFFDR'
        ,p_item_sequence=>189
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'Information Notes&nbsp&nbsp'
        ,p_display_as=>'NATIVE_TEXTAREA'
        ,p_cSize=>100
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'class="expand"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_read_only_when=>'P50_OASYS_NDS_PK'
        ,p_read_only_when_type=>'ITEM_IS_NOT_NULL'
        ,p_read_only_disp_attr=>'class="readOnlyFieldLeft" '
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_03=>'N'
        ,p_attribute_04=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(10853522988570198)
        ,p_name=>'P50_NDS_COMP_DATE'
        ,p_item_sequence=>190
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'Not Discd Sect Comp Date'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>10
        ,p_cMaxlength=>10
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'data-dateitemtype="date" autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(10853630232570198)
        ,p_name=>'P50_OBSOLETED_DATE'
        ,p_item_sequence=>191
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'Superseded Date'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>10
        ,p_cMaxlength=>10
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'data-dateitemtype="date" autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_display_when=>'P50_SUPERSEDE_YN'
        ,p_display_when2=>'Y'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(10853721623570198)
        ,p_name=>'P50_OBSOLETED_REASON'
        ,p_item_sequence=>193
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'Superseded Notes&nbsp&nbsp'
        ,p_display_as=>'NATIVE_TEXTAREA'
        ,p_cSize=>100
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when=>'P50_SUPERSEDE_YN'
        ,p_display_when2=>'Y'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_read_only_when=>'P50_OBSOLETED_DATE'
        ,p_read_only_when_type=>'ITEM_IS_NOT_NULL'
        ,p_read_only_disp_attr=>'class="readOnlyFieldLeft" '
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_03=>'N'
        ,p_attribute_04=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(10853813463570198)
        ,p_name=>'P50_SERVICE_ELM'
        ,p_item_sequence=>194
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'Service Elm'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>50
        ,p_cMaxlength=>50
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(10853909958570199)
        ,p_name=>'P50_SERVICE_CAT'
        ,p_item_sequence=>195
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'Service Cat'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>50
        ,p_cMaxlength=>50
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(10854014234570199)
        ,p_name=>'P50_OFFENDER_PK'
        ,p_item_sequence=>196
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'Offender Pk'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>22
        ,p_cMaxlength=>22
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(10854125746570199)
        ,p_name=>'P50_CHECKSUM'
        ,p_item_sequence=>197
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'Checksum'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>100
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(10854202265570199)
        ,p_name=>'P50_OBSOLETED_AREA'
        ,p_item_sequence=>198
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'Obsoleted Area'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>100
        ,p_cMaxlength=>100
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(10854311845570199)
        ,p_name=>'P50_CREATED_AREA'
        ,p_item_sequence=>199
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_item_default=>'ESX'
        ,p_prompt=>'Created Area'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>100
        ,p_cMaxlength=>100
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(10855631700787908)
        ,p_name=>'P50_SUPERSEDE_YN'
        ,p_item_sequence=>200
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'Supersede YN'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(11179328295097760)
        ,p_name=>'P50_NDS_NAME'
        ,p_item_sequence=>187
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_pre_element_text=>'<br/>'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>250
        ,p_cMaxlength=>250
        ,p_cHeight=>1
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_display_when=>'P50_OASYS_NDS_PK'
        ,p_display_when_type=>'ITEM_IS_NOT_NULL'
        ,p_read_only_when_type=>'ALWAYS'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(11179504878100457)
        ,p_name=>'P50_NDS_SUPERSEDE_NAME'
        ,p_item_sequence=>192
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_pre_element_text=>'<br/>'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>100
        ,p_cMaxlength=>100
        ,p_cHeight=>1
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_display_when=>':P50_SUPERSEDE_YN = ''Y'' AND :P50_OASYS_NDS_PK IS NOT NULL AND :P50_OBSOLETED_DATE IS NOT NULL'
        ,p_display_when_type=>'PLSQL_EXPRESSION'
        ,p_read_only_when_type=>'ALWAYS'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(11198905775296657)
        ,p_name=>'P50_USER_CODE_CREATE'
        ,p_item_sequence=>201
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'User Code Create'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>100
        ,p_cMaxlength=>100
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(11199118242300200)
        ,p_name=>'P50_USER_CODE_OBSOLETED'
        ,p_item_sequence=>202
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_prompt=>'User Code Obsoleted'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>100
        ,p_cMaxlength=>100
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(11215721311323022)
        ,p_name=>'P50_INIT'
        ,p_item_sequence=>203
        ,p_item_plug_id=>wwv_flow_api.id(10852931457570180)
        ,p_item_default=>'Y'
        ,p_prompt=>'Init'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_validation(
            p_id=>wwv_flow_api.id(10947019406484982)
        ,p_validation_name=>'Supersede Validation'
        ,p_validation_sequence=>10
        ,p_validation=>'return intbdtto_pkg.validate_intbdtto;'
        ,p_validation_type=>'FUNC_BODY_RETURNING_ERR_TEXT'
        ,p_always_execute=>'N'
        ,p_validation_condition=>':REQUEST IN (''SAVE'',''CONFIRM_DELETE'')'
        ,p_validation_condition_type=>'PLSQL_EXPRESSION'
        ,p_error_display_location=>'INLINE_IN_NOTIFICATION'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(10934008018683917)
        ,p_process_sequence=>10
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'Save NDS Info'
        ,p_process_sql_clob=>'intbdtto_pkg.save_intbdtto;'
        ,p_process_when=>':REQUEST IN (''SAVE'', ''CONFIRM_DELETE'')'
        ,p_process_when_type=>'PLSQL_EXPRESSION'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(10946630263421957)
        ,p_process_sequence=>20
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'Set Supersede flag'
        ,p_process_sql_clob=>':P50_SUPERSEDE_YN := ''Y'';'
        ,p_process_when=>'SUPERSEDE'
        ,p_process_when_type=>'REQUEST_EQUALS_CONDITION'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(1744312305509514)
        ,p_process_sequence=>40
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'Set Delete flag'
        ,p_process_sql_clob=>':P50_DELETE_YN := ''Y'';'
        ,p_process_when=>'DELETE'
        ,p_process_when_type=>'REQUEST_EQUALS_CONDITION'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(13365507644889701)
        ,p_process_sequence=>110
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_SESSION_STATE'
        ,p_process_name=>'clear_cache_50'
        ,p_attribute_01=>'CLEAR_CACHE_CURRENT_PAGE'
        ,p_process_when=>':REQUEST in (''CLOSE'',''CONFIRM_DELETE'')'
        ,p_process_when_type=>'PLSQL_EXPRESSION'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(10876110537760155)
        ,p_process_sequence=>10
        ,p_process_point=>'BEFORE_HEADER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P10_OLBH'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'clog_api.ins (p_source => ''MICHAEL'', p_text => ''step 1'');',
            'IF NVL(:P50_DELETE_YN,''N'') <> ''Y''',
            'THEN',
            ' intbdtto_pkg.p10_intbdtto_olbh;',
            'END IF;',
            'clog_api.ins (p_source => ''MICHAEL'', p_text => ''step 2'');'))
        ,p_process_when=>'NVL(:P50_INIT,''Y'') = ''Y'' '
        ,p_process_when_type=>'PLSQL_EXPRESSION'
        ,p_process_when2=>'Y'
        );
end;
/
prompt --application/pages/page_00060
begin
    wwv_flow_api.create_page(
            p_id=>60
        ,p_user_interface_id=>wwv_flow_api.id(74312104640072)
        ,p_name=>'Request for Access'
        ,p_page_mode=>'NORMAL'
        ,p_step_title=>'Request for Access'
        ,p_step_sub_title_type=>'TEXT_WITH_SUBSTITUTIONS'
        ,p_first_item=>'NO_FIRST_ITEM'
        ,p_autocomplete_on_off=>'OFF'
        ,p_group_id=>wwv_flow_api.id(6767457294623073)
        ,p_step_template=>wwv_flow_api.id(20789413245320561)
        ,p_page_template_options=>'#DEFAULT#'
        ,p_required_role=>wwv_flow_api.id(5052631587350853)
        ,p_dialog_chained=>'Y'
        ,p_overwrite_navigation_list=>'N'
        ,p_nav_list_template_options=>'#DEFAULT#'
        ,p_page_is_public_y_n=>'N'
        ,p_protection_level=>'D'
        ,p_cache_mode=>'NOCACHE'
        ,p_cache_timeout_seconds=>21600
        ,p_last_upd_yyyymmddhh24miss=>'20190425093143'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(1555720255267957)
        ,p_plug_name=>'Request for Access'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(20790696787320563)
        ,p_plug_display_sequence=>30
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(1556628045378304)
        ,p_plug_name=>'Offender Details'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(20790696787320563)
        ,p_plug_display_sequence=>20
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(14932109927571701)
        ,p_plug_name=>'Users Requiring Access'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_escape_on_http_output=>'Y'
        ,p_plug_template=>wwv_flow_api.id(20790696787320563)
        ,p_plug_display_sequence=>40
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_source_type=>'PLUGIN_COM.SUNGARD.COLLECTION_SHUTTLE_REGION'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_plug_display_condition_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_plug_display_when_condition=>'P60_TRUSTEE_ACCESS_YN'
        ,p_plug_display_when_cond2=>'N'
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_attribute_01=>'OFF030_USERS'
        ,p_attribute_02=>'10'
        ,p_attribute_03=>'Y'
        ,p_attribute_04=>'Y'
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(6428012674196627)
        ,p_button_sequence=>10
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P60_BT_SUBMIT'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Submit'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_button_condition=>'RETURN mod_OFF030_pkg.p10_display_save_button;'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(6428229643201528)
        ,p_button_sequence=>20
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P60_BT_CANCEL'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Cancel'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(13366029939905655)
        ,p_branch_action=>'10'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>40
        ,p_branch_condition_type=>'REQUEST_IN_CONDITION'
        ,p_branch_condition=>'P60_BT_CANCEL,P60_BT_SUBMIT'
        ,p_branch_comment=>'Created 23-MAR-2011 11:04 by KGAMBLE'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(7145528340188481)
        ,p_branch_action=>'60'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>1029
        ,p_branch_comment=>'Created 17-SEP-2010 11:58 by ZIPPYB'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1556811146378312)
        ,p_name=>'P60_DATE_OF_BIRTH'
        ,p_item_sequence=>145
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Date Of Birth'
        ,p_source=>'P10_DATE_OF_BIRTH'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>10
        ,p_cMaxlength=>10
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY data-dateitemtype="date"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1557007635378326)
        ,p_name=>'P60_FAMILY_NAME'
        ,p_item_sequence=>10
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Surname'
        ,p_source=>'P10_FAMILY_NAME'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>32
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1557227607378326)
        ,p_name=>'P60_FORENAME_1'
        ,p_item_sequence=>40
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Forename'
        ,p_source=>'P10_FORENAME_1'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>32
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1557601469378328)
        ,p_name=>'P60_PNC'
        ,p_item_sequence=>20
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'PNC'
        ,p_source=>'P10_PNC'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>32
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1557803413378328)
        ,p_name=>'P60_CMS_PROB_NUMBER'
        ,p_item_sequence=>130
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Probation CRN'
        ,p_source=>'P10_CMS_PROB_NUMBER'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>20
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1558003909378328)
        ,p_name=>'P60_CRO_NUMBER'
        ,p_item_sequence=>60
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'CRO'
        ,p_source=>'P10_CRO_NUMBER'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>11
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1558228750378328)
        ,p_name=>'P60_PRISON_NUMBER'
        ,p_item_sequence=>170
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Prison LIDS No'
        ,p_source=>'P10_PRISON_NUMBER'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>6
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1558412969378329)
        ,p_name=>'P60_MERGE_PNC_NUMBER'
        ,p_item_sequence=>45
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Merge PNC'
        ,p_source=>'P10_MERGE_PNC_NUMBER'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>20
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1558612716378329)
        ,p_name=>'P60_DATE_OF_DEATH'
        ,p_item_sequence=>220
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Date Of Death'
        ,p_source=>'P10_DATE_OF_DEATH'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>10
        ,p_cMaxlength=>15
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY data-dateitemtype="date"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when=>'P60_DECEASED_IND'
        ,p_display_when2=>'Y'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1559031484378329)
        ,p_name=>'P60_DATE_OF_DEPORTATION'
        ,p_item_sequence=>240
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Date Of Deportation'
        ,p_source=>'P10_DATE_OF_DEPORTATION'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>10
        ,p_cMaxlength=>15
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY data-dateitemtype="date"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when=>'P60_PHYSICAL_LOCATION'
        ,p_display_when2=>'DEPORTED'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1559419757378332)
        ,p_name=>'P60_GENDER_ELM'
        ,p_item_sequence=>160
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Gender'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>10
        ,p_cMaxlength=>10
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_read_only_disp_attr=>'class="readOnlyFieldLeft"'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1560009516378334)
        ,p_name=>'P60_DECEASED_IND'
        ,p_item_sequence=>148
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Deceased Ind'
        ,p_source=>'P10_DECEASED_IND'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>10
        ,p_cMaxlength=>10
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1560219163378334)
        ,p_name=>'P60_PRIMARY_LOC_AREA_DESC'
        ,p_item_sequence=>70
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Owning Area/Est'
        ,p_source=>'P10_PRIMARY_LOC_AREA_DESC'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>35
        ,p_cMaxlength=>100
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1560404659378334)
        ,p_name=>'P60_PHYSICAL_LOCATION'
        ,p_item_sequence=>140
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Location'
        ,p_source=>'P10_PHYSICAL_LOCATION'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>35
        ,p_cMaxlength=>100
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1560619565378335)
        ,p_name=>'P60_CMS_PRIS_NUMBER'
        ,p_item_sequence=>147
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Prison NOMIS No'
        ,p_source=>'P10_CMS_PRIS_NUMBER'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1560827967378340)
        ,p_name=>'P60_LAO_TEL_NO'
        ,p_item_sequence=>65
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'LAO Contact No'
        ,p_source=>'P10_LAO_TEL_NO'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>35
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'class="readOnlyFieldLeft"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_display_when=>'P60_LIMITED_ACCESS_OFFENDER'
        ,p_display_when2=>'Y'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1561023451378340)
        ,p_name=>'P60_DUMMY'
        ,p_item_sequence=>200
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_display_as=>'NATIVE_DISPLAY_ONLY'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_escape_on_http_output=>'N'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'VALUE'
        ,p_attribute_04=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1561425259378340)
        ,p_name=>'P60_DOB_MAND'
        ,p_item_sequence=>250
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1561627354378340)
        ,p_name=>'P60_ADDRESS_MAND'
        ,p_item_sequence=>260
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1561805812378342)
        ,p_name=>'P60_POSTCODE_MAND'
        ,p_item_sequence=>270
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1562013496378342)
        ,p_name=>'P60_ETHNIC_MAND'
        ,p_item_sequence=>280
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1562213994378342)
        ,p_name=>'P60_ICONS'
        ,p_item_sequence=>30
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_source=>'P10_ICONS'
        ,p_source_type=>'ITEM'
        ,p_display_as=>'NATIVE_DISPLAY_ONLY'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'CENTER'
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_protection_level=>'I'
        ,p_escape_on_http_output=>'N'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'VALUE'
        ,p_attribute_04=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1562413707378342)
        ,p_name=>'P60_LAO_ENABLED_FLAG'
        ,p_item_sequence=>290
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>2000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1562614730378342)
        ,p_name=>'P60_PRIMARY_LOCATION_AREA'
        ,p_item_sequence=>300
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Owning Area/Est'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>40
        ,p_cMaxlength=>100
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1569627782709393)
        ,p_name=>'P60_USER_TEAM'
        ,p_item_sequence=>10
        ,p_item_plug_id=>wwv_flow_api.id(14932109927571701)
        ,p_prompt=>'User Team'
        ,p_display_as=>'NATIVE_SELECT_LIST'
        ,p_lov=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'return mod_off030_pkg.p60_lov_user_team;',
            ''))
        ,p_lov_display_null=>'YES'
        ,p_lov_null_text=>' - Select Team - '
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'data-notreadonly="true"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_display_when=>'P60_TRUSTEE_ACCESS_YN'
        ,p_display_when2=>'N'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NONE'
        ,p_attribute_02=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6417323120953642)
        ,p_name=>'P60_RFA_REASON'
        ,p_item_sequence=>80
        ,p_item_plug_id=>wwv_flow_api.id(1555720255267957)
        ,p_prompt=>'RFA Reason'
        ,p_display_as=>'NATIVE_SELECT_LIST'
        ,p_lov=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'return elements_pkg.return_lov(''RFA_REASON_FOR_REQUEST'', :P60_RFA_REASON);',
            ''))
        ,p_lov_display_null=>'YES'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'data-notreadonly="true"'
        ,p_colspan=>5
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NONE'
        ,p_attribute_02=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6419320096009539)
        ,p_name=>'P60_RFA_REASON_OTHER'
        ,p_item_sequence=>90
        ,p_item_plug_id=>wwv_flow_api.id(1555720255267957)
        ,p_prompt=>'Other Reason'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>60
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>2
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6423601499136589)
        ,p_name=>'P60_REQUESTER_COMMENTS'
        ,p_item_sequence=>100
        ,p_item_plug_id=>wwv_flow_api.id(1555720255267957)
        ,p_prompt=>'Requester Comments'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>60
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'data-notreadonly="true"'
        ,p_colspan=>5
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_display_when=>'P60_TRUSTEE_ACCESS_YN'
        ,p_display_when2=>'N'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6425900244145709)
        ,p_name=>'P60_RFA_EXPIRY'
        ,p_item_sequence=>110
        ,p_item_plug_id=>wwv_flow_api.id(1555720255267957)
        ,p_prompt=>'RFA length (Days)'
        ,p_display_as=>'NATIVE_NUMBER_FIELD'
        ,p_cSize=>5
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'data-notreadonly="true"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_display_when=>'P60_TRUSTEE_ACCESS_YN'
        ,p_display_when2=>'N'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_read_only_when=>'P60_TRUSTEE_ACCESS_YN'
        ,p_read_only_when2=>'N'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_03=>'right'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6426724055171485)
        ,p_name=>'P60_STATIC_TEXT'
        ,p_item_sequence=>120
        ,p_item_plug_id=>wwv_flow_api.id(1555720255267957)
        ,p_source=>'You are requesting access to the offender named above please enter a reason for the request and note that this request will be audited.'
        ,p_source_type=>'STATIC'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>145
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'class="readOnlyFieldLeft" READONLY'
        ,p_colspan=>5
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_display_when=>'P60_TRUSTEE_ACCESS_YN'
        ,p_display_when2=>'N'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6427304578184808)
        ,p_name=>'P60_STATIC_TEXT_TRUSTY'
        ,p_item_sequence=>130
        ,p_item_plug_id=>wwv_flow_api.id(1555720255267957)
        ,p_source=>'This record is outside of your organisational remit.  You can gain access by providing a reason however your actions will be audited.'
        ,p_source_type=>'STATIC'
        ,p_display_as=>'NATIVE_DISPLAY_ONLY'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_display_when=>'P60_TRUSTEE_ACCESS_YN'
        ,p_display_when2=>'Y'
        ,p_display_when_type=>'VAL_OF_ITEM_IN_COND_EQ_COND2'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'VALUE'
        ,p_attribute_04=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(7076102951454579)
        ,p_name=>'P60_TRUSTEE_ACCESS_YN'
        ,p_item_sequence=>140
        ,p_item_plug_id=>wwv_flow_api.id(1555720255267957)
        ,p_prompt=>'Trustee Access Yn'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(7081710962185453)
        ,p_name=>'P60_READONLY'
        ,p_item_sequence=>150
        ,p_item_plug_id=>wwv_flow_api.id(1555720255267957)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>4000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(8259826190540452)
        ,p_name=>'P60_ETHNIC_CAT_ELM_DESC'
        ,p_item_sequence=>231
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Ethnic Category'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_colspan=>3
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(8970127736334180)
        ,p_name=>'P60_DUMMY_2'
        ,p_item_sequence=>110
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_display_as=>'NATIVE_DISPLAY_ONLY'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'VALUE'
        ,p_attribute_04=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(11484402436336888)
        ,p_name=>'P60_OTHER_FORENAMES'
        ,p_item_sequence=>49
        ,p_item_plug_id=>wwv_flow_api.id(1556628045378304)
        ,p_prompt=>'Other Forenames'
        ,p_display_as=>'NATIVE_TEXT_FIELD'
        ,p_cSize=>30
        ,p_cMaxlength=>32
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_tag_attributes=>'autocomplete="off" class="readOnlyFieldLeft" READONLY'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'N'
        ,p_attribute_04=>'TEXT'
        ,p_attribute_05=>'NONE'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(14914624576658119)
        ,p_name=>'P60_CTX_CT_AREA_EST_CODE'
        ,p_item_sequence=>310
        ,p_item_plug_id=>wwv_flow_api.id(5239055396194975)
        ,p_use_cache_before_default=>'NO'
        ,p_source=>'app_ctx_pkg.get (''CT_AREA_EST_CODE'')'
        ,p_source_type=>'FUNCTION'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'Y'
        ,p_item_comment=>'Holds the user''s context Trust/Establishment code, for use in LOVs etc.'
        );
    wwv_flow_api.create_page_validation(
            p_id=>wwv_flow_api.id(7970719961225908)
        ,p_validation_name=>'Validate RFA'
        ,p_validation_sequence=>10
        ,p_validation=>'return mod_off030_pkg.p60_validate_rfa;'
        ,p_validation_type=>'FUNC_BODY_RETURNING_ERR_TEXT'
        ,p_always_execute=>'N'
        ,p_when_button_pressed=>wwv_flow_api.id(6428012674196627)
        ,p_error_display_location=>'INLINE_WITH_FIELD_AND_NOTIFICATION'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(14937332680795932)
        ,p_name=>'Populate user shuttle'
        ,p_event_sequence=>10
        ,p_triggering_element_type=>'ITEM'
        ,p_triggering_element=>'P60_USER_TEAM'
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'change'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(14937628298795940)
        ,p_event_id=>wwv_flow_api.id(14937332680795932)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_EXECUTE_PLSQL_CODE'
        ,p_attribute_01=>'mod_off030_pkg.p60_populate_user_shuttle;'
        ,p_attribute_02=>'P60_USER_TEAM'
        ,p_stop_execution_on_error=>'Y'
        ,p_wait_for_result=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(14937809262798580)
        ,p_event_id=>wwv_flow_api.id(14937332680795932)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>20
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_attribute_01=>'refreshShuttle(''OFF030_USERS'');'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(14949809392158226)
        ,p_name=>'Show/hide Other Reason'
        ,p_event_sequence=>20
        ,p_triggering_element_type=>'ITEM'
        ,p_triggering_element=>'P60_RFA_REASON'
        ,p_triggering_condition_type=>'EQUALS'
        ,p_triggering_expression=>'3'
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'change'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(14950100625158226)
        ,p_event_id=>wwv_flow_api.id(14949809392158226)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_SHOW'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P60_RFA_REASON_OTHER'
        ,p_attribute_01=>'N'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(14950308421158227)
        ,p_event_id=>wwv_flow_api.id(14949809392158226)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>20
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_HIDE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P60_RFA_REASON_OTHER'
        ,p_attribute_01=>'N'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(7039022374569142)
        ,p_process_sequence=>50
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'Save RFA Request'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'IF :P60_TRUSTEE_ACCESS_YN = ''N''',
            'THEN',
            '  mod_off030_pkg.p60_save_rfa_request;',
            'ELSE',
            '  -- Record audit event',
            '  mod_off030_pkg.p60_audit_trusty_rfa_request;',
            'END IF;',
            ':P10_TRUSTEE_ACCESS_YN := :P60_TRUSTEE_ACCESS_YN;',
            ':P10_INIT := ''N'';'))
        ,p_process_when_button_id=>wwv_flow_api.id(6428012674196627)
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(13366613793910423)
        ,p_process_sequence=>60
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_SESSION_STATE'
        ,p_process_name=>'clear_cache_60'
        ,p_attribute_01=>'CLEAR_CACHE_CURRENT_PAGE'
        ,p_process_when=>'P60_BT_CANCEL,P60_BT_SUBMIT'
        ,p_process_when_type=>'REQUEST_IN_CONDITION'
        );
end;
/
begin
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(7008427486839057)
        ,p_process_sequence=>10
        ,p_process_point=>'BEFORE_HEADER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P60_OLBH'
        ,p_process_sql_clob=>'mod_off030_pkg.p60_olbh;'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(1768411579852643)
        ,p_process_sequence=>20
        ,p_process_point=>'BEFORE_HEADER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P60_OLBH_INIT_COLL'
        ,p_process_sql_clob=>'mod_off030_pkg.p60_init_user_coll;'
        ,p_process_is_stateful_y_n=>'Y'
        );
end;
/
prompt --application/pages/page_00070
begin
    wwv_flow_api.create_page(
            p_id=>70
        ,p_user_interface_id=>wwv_flow_api.id(74312104640072)
        ,p_name=>'Request for Access - Rescind'
        ,p_page_mode=>'NORMAL'
        ,p_step_title=>'Request for Access - Rescind'
        ,p_step_sub_title_type=>'TEXT_WITH_SUBSTITUTIONS'
        ,p_first_item=>'NO_FIRST_ITEM'
        ,p_autocomplete_on_off=>'OFF'
        ,p_group_id=>wwv_flow_api.id(6767457294623073)
        ,p_javascript_code=>'var htmldb_delete_message=''"DELETE_CONFIRM_MSG"'';'
        ,p_step_template=>wwv_flow_api.id(20789413245320561)
        ,p_page_template_options=>'#DEFAULT#'
        ,p_required_role=>wwv_flow_api.id(5052631587350853)
        ,p_dialog_chained=>'Y'
        ,p_overwrite_navigation_list=>'N'
        ,p_nav_list_template_options=>'#DEFAULT#'
        ,p_page_is_public_y_n=>'N'
        ,p_protection_level=>'C'
        ,p_cache_mode=>'NOCACHE'
        ,p_cache_timeout_seconds=>21600
        ,p_last_updated_by=>'PAULA'
        ,p_last_upd_yyyymmddhh24miss=>'20140718102510'
        );
    wwv_flow_api.create_report_region(
            p_id=>wwv_flow_api.id(1666227075269904)
        ,p_name=>'Rescind Request for Access'
        ,p_template=>wwv_flow_api.id(20790696787320563)
        ,p_display_sequence=>25
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_new_grid_row=>false
        ,p_new_grid_column=>false
        ,p_display_column=>1
        ,p_display_point=>'BODY_3'
        ,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'return /* select 1 from dual */ mod_off030_pkg.p70_rescind_query;',
            ''))
        ,p_source_type=>'NATIVE_FNC_REPORT'
        ,p_ajax_enabled=>'Y'
        ,p_fixed_header=>'NONE'
        ,p_query_row_template=>wwv_flow_api.id(20794602676320568)
        ,p_query_num_rows=>15
        ,p_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_query_show_nulls_as=>' - '
        ,p_query_break_cols=>'0'
        ,p_query_no_data_found=>'no current RFA''s for this offender'
        ,p_query_num_rows_type=>'SEARCH_ENGINE'
        ,p_query_row_count_max=>500
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_break_type_flag=>'DEFAULT_BREAK_FORMATTING'
        ,p_csv_output=>'N'
        ,p_prn_output=>'N'
        ,p_prn_format=>'PDF'
        ,p_prn_output_show_link=>'Y'
        ,p_prn_output_link_text=>'Print'
        ,p_prn_content_disposition=>'ATTACHMENT'
        ,p_prn_document_header=>'APEX'
        ,p_prn_units=>'MILLIMETERS'
        ,p_prn_paper_size=>'A4'
        ,p_prn_width_units=>'PERCENTAGE'
        ,p_prn_width=>297
        ,p_prn_height=>210
        ,p_prn_orientation=>'HORIZONTAL'
        ,p_prn_page_header_font_color=>'#000000'
        ,p_prn_page_header_font_family=>'Helvetica'
        ,p_prn_page_header_font_weight=>'normal'
        ,p_prn_page_header_font_size=>'12'
        ,p_prn_page_footer_font_color=>'#000000'
        ,p_prn_page_footer_font_family=>'Helvetica'
        ,p_prn_page_footer_font_weight=>'normal'
        ,p_prn_page_footer_font_size=>'12'
        ,p_prn_header_bg_color=>'#9bafde'
        ,p_prn_header_font_color=>'#ffffff'
        ,p_prn_header_font_family=>'Helvetica'
        ,p_prn_header_font_weight=>'normal'
        ,p_prn_header_font_size=>'10'
        ,p_prn_body_bg_color=>'#efefef'
        ,p_prn_body_font_color=>'#000000'
        ,p_prn_body_font_family=>'Helvetica'
        ,p_prn_body_font_weight=>'normal'
        ,p_prn_body_font_size=>'10'
        ,p_prn_border_width=>.5
        ,p_prn_page_header_alignment=>'CENTER'
        ,p_prn_page_footer_alignment=>'CENTER'
        ,p_query_asc_image=>'apex/builder/dup.gif'
        ,p_query_asc_image_attr=>'width="16" height="16"  '
        ,p_query_desc_image=>'apex/builder/ddown.gif'
        ,p_query_desc_image_attr=>'width="16" height="16"  '
        ,p_plug_query_strip_html=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(1666521125269931)
        ,p_query_column_id=>1
        ,p_column_alias=>'AREA_EST_NAME'
        ,p_column_display_sequence=>2
        ,p_column_heading=>'Probation Provider/Establishment'
        ,p_use_as_row_header=>'N'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'16'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(1666613193269931)
        ,p_query_column_id=>2
        ,p_column_alias=>'TASK_RELATED_ITEM_PK'
        ,p_column_display_sequence=>1
        ,p_column_heading=>'<input type ="checkbox" onclick="$f_CheckFirstColumn(this)" />'
        ,p_column_html_expression=>'<input type="checkbox" #TASK_RELATED_ITEM_PK# value="#TASK_RELATED_ITEM_PK#" name="f40" id="f40_#ROWNUM#"/>'
        ,p_heading_alignment=>'LEFT'
        ,p_lov_show_nulls=>'NO'
        ,p_pk_col_source_type=>'T'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'16'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(1666702505269931)
        ,p_query_column_id=>3
        ,p_column_alias=>'FORENAME'
        ,p_column_display_sequence=>3
        ,p_column_heading=>'Forename'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'16'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(1666831695269931)
        ,p_query_column_id=>4
        ,p_column_alias=>'USER_FAMILY_NAME'
        ,p_column_display_sequence=>4
        ,p_column_heading=>'Surname'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'16'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(1666914689269931)
        ,p_query_column_id=>5
        ,p_column_alias=>'OASYS_USER_CODE'
        ,p_column_display_sequence=>5
        ,p_column_heading=>'Username'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'16'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(1667008955269931)
        ,p_query_column_id=>6
        ,p_column_alias=>'ROW_KEY'
        ,p_column_display_sequence=>6
        ,p_column_heading=>'ROW_KEY'
        ,p_hidden_column=>'Y'
        ,p_include_in_export=>'N'
        ,p_print_col_width=>'0'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(1667118113269931)
        ,p_query_column_id=>7
        ,p_column_alias=>'RFA_EXPIRY_DATE'
        ,p_column_display_sequence=>7
        ,p_column_heading=>'RFA Expiry Date'
        ,p_include_in_export=>'Y'
        ,p_print_col_width=>'16'
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(1652513974507545)
        ,p_button_sequence=>10
        ,p_button_plug_id=>wwv_flow_api.id(1666227075269904)
        ,p_button_name=>'CANCEL'
        ,p_button_action=>'REDIRECT_PAGE'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Close'
        ,p_button_position=>'TOP'
        ,p_button_redirect_url=>'f?p=&APP_ID.:10:&SESSION.::&DEBUG.:::'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(1652610441507545)
        ,p_button_sequence=>30
        ,p_button_plug_id=>wwv_flow_api.id(1666227075269904)
        ,p_button_name=>'SUBMIT'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Submit'
        ,p_button_position=>'TOP'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(11577809743460152)
        ,p_branch_action=>'70'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>11
        ,p_branch_comment=>'Created 11-NOV-2010 13:56 by ZIPPYB'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(1668409170397209)
        ,p_process_sequence=>40
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'Save Rescind RFA Request'
        ,p_process_sql_clob=>'mod_off030_pkg.p70_rfa_rescind_request;'
        ,p_process_when_button_id=>wwv_flow_api.id(1652610441507545)
        );
end;
/
prompt --application/pages/page_00080
begin
    wwv_flow_api.create_page(
            p_id=>80
        ,p_user_interface_id=>wwv_flow_api.id(74312104640072)
        ,p_name=>'View Active Request for Access'
        ,p_page_mode=>'NORMAL'
        ,p_step_title=>'View Active Request for Access'
        ,p_step_sub_title=>'View Active Request for Access'
        ,p_step_sub_title_type=>'TEXT_WITH_SUBSTITUTIONS'
        ,p_first_item=>'NO_FIRST_ITEM'
        ,p_autocomplete_on_off=>'OFF'
        ,p_group_id=>wwv_flow_api.id(6767457294623073)
        ,p_javascript_code=>'var htmldb_delete_message=''"DELETE_CONFIRM_MSG"'';'
        ,p_step_template=>wwv_flow_api.id(20789413245320561)
        ,p_page_template_options=>'#DEFAULT#'
        ,p_required_role=>wwv_flow_api.id(5052631587350853)
        ,p_dialog_chained=>'Y'
        ,p_overwrite_navigation_list=>'N'
        ,p_nav_list_template_options=>'#DEFAULT#'
        ,p_page_is_public_y_n=>'N'
        ,p_protection_level=>'C'
        ,p_cache_mode=>'NOCACHE'
        ,p_cache_timeout_seconds=>21600
        ,p_last_updated_by=>'PAULA'
        ,p_last_upd_yyyymmddhh24miss=>'20140710095208'
        );
    wwv_flow_api.create_report_region(
            p_id=>wwv_flow_api.id(8419505249104926)
        ,p_name=>'Request for Access'
        ,p_template=>wwv_flow_api.id(20790696787320563)
        ,p_display_sequence=>25
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_new_grid_row=>false
        ,p_new_grid_column=>false
        ,p_display_column=>1
        ,p_display_point=>'BODY_3'
        ,p_source=>'return /* select 1 from dual */ mod_off030_pkg.p80_rfa_query;'
        ,p_source_type=>'NATIVE_FNC_REPORT'
        ,p_ajax_enabled=>'Y'
        ,p_fixed_header=>'NONE'
        ,p_query_row_template=>wwv_flow_api.id(20794602676320568)
        ,p_query_num_rows=>15
        ,p_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_query_show_nulls_as=>' - '
        ,p_query_break_cols=>'0'
        ,p_query_no_data_found=>'no data found'
        ,p_query_num_rows_type=>'SEARCH_ENGINE'
        ,p_query_row_count_max=>500
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_break_type_flag=>'DEFAULT_BREAK_FORMATTING'
        ,p_csv_output=>'N'
        ,p_prn_output=>'N'
        ,p_query_asc_image=>'apex/builder/dup.gif'
        ,p_query_asc_image_attr=>'width="16" height="16"  '
        ,p_query_desc_image=>'apex/builder/ddown.gif'
        ,p_query_desc_image_attr=>'width="16" height="16"  '
        ,p_plug_query_strip_html=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(8420022724104926)
        ,p_query_column_id=>1
        ,p_column_alias=>'AREA_EST_NAME'
        ,p_column_display_sequence=>1
        ,p_column_heading=>'Probation Trust/Establishment'
        ,p_heading_alignment=>'LEFT'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(8420222152104926)
        ,p_query_column_id=>2
        ,p_column_alias=>'FORENAME'
        ,p_column_display_sequence=>2
        ,p_column_heading=>'Forename'
        ,p_heading_alignment=>'LEFT'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(8420302703104926)
        ,p_query_column_id=>3
        ,p_column_alias=>'USER_FAMILY_NAME'
        ,p_column_display_sequence=>3
        ,p_column_heading=>'Surname'
        ,p_heading_alignment=>'LEFT'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(8419725349104926)
        ,p_query_column_id=>4
        ,p_column_alias=>'OASYS_USER_CODE'
        ,p_column_display_sequence=>4
        ,p_column_heading=>'Username'
        ,p_heading_alignment=>'LEFT'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(8419826699104926)
        ,p_query_column_id=>5
        ,p_column_alias=>'ROW_KEY'
        ,p_column_display_sequence=>5
        ,p_column_heading=>'ROW_KEY'
        ,p_heading_alignment=>'LEFT'
        ,p_hidden_column=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(8419926996104926)
        ,p_query_column_id=>6
        ,p_column_alias=>'RFA_EXPIRY_DATE'
        ,p_column_display_sequence=>6
        ,p_column_heading=>'RFA Expiry Date'
        ,p_heading_alignment=>'LEFT'
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(8420405562104926)
        ,p_button_sequence=>10
        ,p_button_plug_id=>wwv_flow_api.id(8419505249104926)
        ,p_button_name=>'CLOSE'
        ,p_button_action=>'REDIRECT_PAGE'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Close'
        ,p_button_position=>'TOP'
        ,p_button_redirect_url=>'f?p=&APP_ID.:10:&SESSION.::&DEBUG.:::'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(13366908037918213)
        ,p_branch_action=>'80'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>11
        ,p_branch_comment=>'Created 23-MAR-2011 11:06 by KGAMBLE'
        );
end;
/
prompt --application/pages/page_00090
begin
    wwv_flow_api.create_page(
            p_id=>90
        ,p_user_interface_id=>wwv_flow_api.id(74312104640072)
        ,p_name=>'Limited Access Offender'
        ,p_page_mode=>'NORMAL'
        ,p_step_title=>'Limited Access Offender'
        ,p_step_sub_title_type=>'TEXT_WITH_SUBSTITUTIONS'
        ,p_first_item=>'NO_FIRST_ITEM'
        ,p_autocomplete_on_off=>'OFF'
        ,p_group_id=>wwv_flow_api.id(6767457294623073)
        ,p_javascript_code=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'function appdosubmitconfirm (pRequest) {',
            '',
            ' var newCode = $v(''P90_LAO_STATUS'');',
            ' var oldCode = $v(''P90_ORIGINAL_LAO_STATUS'');',
            '',
            ' if (newCode   == ''N''',
            '    && oldCode == ''Y'')',
            '  {',
            '  return (confirm("You are about to remove the LAO status of the offender. The offender will then be available to view. Do you wish to continue?"))',
            ' }',
            ' else',
            ' {',
            ' return true;',
            ' }',
            '}',
            ''))
        ,p_step_template=>wwv_flow_api.id(20789413245320561)
        ,p_page_template_options=>'#DEFAULT#'
        ,p_required_role=>wwv_flow_api.id(5052631587350853)
        ,p_dialog_chained=>'Y'
        ,p_overwrite_navigation_list=>'N'
        ,p_nav_list_template_options=>'#DEFAULT#'
        ,p_page_is_public_y_n=>'N'
        ,p_protection_level=>'D'
        ,p_cache_mode=>'NOCACHE'
        ,p_cache_timeout_seconds=>21600
        ,p_last_upd_yyyymmddhh24miss=>'20190425093143'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(1875430601165878)
        ,p_plug_name=>'Limited Access Offender'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(20790696787320563)
        ,p_plug_display_sequence=>10
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(6609319600608685)
        ,p_plug_name=>'LAO Readers'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_escape_on_http_output=>'Y'
        ,p_plug_template=>wwv_flow_api.id(20790696787320563)
        ,p_plug_display_sequence=>20
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_source_type=>'PLUGIN_COM.SUNGARD.COLLECTION_SHUTTLE_REGION'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_attribute_01=>'OFF030_USERS'
        ,p_attribute_02=>'10'
        ,p_attribute_03=>'Y'
        ,p_attribute_04=>'Y'
        ,p_attribute_05=>'350'
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(1894600079927218)
        ,p_button_sequence=>10
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P90_BT_SAVE'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Save'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_button_condition=>'RETURN mod_OFF030_pkg.p10_display_save_button;'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(1894809429929909)
        ,p_button_sequence=>20
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P90_BT_CLOSE'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790303721320563)
        ,p_button_image_alt=>'Close'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_button_execute_validations=>'N'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(1895609237957328)
        ,p_branch_action=>'10'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>10
        ,p_branch_condition_type=>'PLSQL_EXPRESSION'
        ,p_branch_condition=>'app_navigation_pkg.close_request_check'
        ,p_branch_comment=>'Created 11-OCT-2010 00:35 by ZIPPYB'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(6192508556484394)
        ,p_branch_action=>'&APP_PAGE_ID.'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>99
        ,p_branch_comment=>'Created 02-FEB-2011 10:54 by TANDREWS'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1875702209180896)
        ,p_name=>'P90_LAO_STATUS'
        ,p_item_sequence=>10
        ,p_item_plug_id=>wwv_flow_api.id(1875430601165878)
        ,p_prompt=>'Set LAO Status'
        ,p_display_as=>'NATIVE_SELECT_LIST'
        ,p_lov=>'STATIC2:Yes;Y,No;N'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'NONE'
        ,p_attribute_02=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1876830531283756)
        ,p_name=>'P90_CT_AREA_EST_CODE'
        ,p_item_sequence=>20
        ,p_item_plug_id=>wwv_flow_api.id(1875430601165878)
        ,p_prompt=>'Provider/Est Location'
        ,p_display_as=>'NATIVE_SELECT_LIST'
        ,p_lov=>'return lao_pkg.p90_area_lov_query;'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_cattributes_element=>'style="width:100%;"'
        ,p_colspan=>2
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'NONE'
        ,p_attribute_02=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1877030194359301)
        ,p_name=>'P90_DIVISION_CODE'
        ,p_item_sequence=>30
        ,p_item_plug_id=>wwv_flow_api.id(1875430601165878)
        ,p_prompt=>'LDU'
        ,p_display_as=>'NATIVE_SELECT_LIST'
        ,p_lov=>'return mod_off030_pkg.p90_lov_division_code;'
        ,p_lov_display_null=>'YES'
        ,p_lov_cascade_parent_items=>'P90_CT_AREA_EST_CODE'
        ,p_ajax_optimize_refresh=>'Y'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_cattributes_element=>'style="width:100%;"'
        ,p_colspan=>2
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'NONE'
        ,p_attribute_02=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(1877207908419071)
        ,p_name=>'P90_TEAM_CODE'
        ,p_item_sequence=>40
        ,p_item_plug_id=>wwv_flow_api.id(1875430601165878)
        ,p_prompt=>'Team'
        ,p_display_as=>'NATIVE_SELECT_LIST'
        ,p_lov=>'return mod_off030_pkg.p90_lov_team_code(''P90_TEAM_CODE'');'
        ,p_lov_display_null=>'YES'
        ,p_lov_cascade_parent_items=>'P90_DIVISION_CODE'
        ,p_ajax_optimize_refresh=>'Y'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_cattributes_element=>'style="width:100%;"'
        ,p_colspan=>2
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'YES'
        ,p_attribute_01=>'NONE'
        ,p_attribute_02=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(8692417447351022)
        ,p_name=>'P90_INIT'
        ,p_item_sequence=>90
        ,p_item_plug_id=>wwv_flow_api.id(5239055396194975)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cMaxlength=>4000
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(12486529083999290)
        ,p_name=>'P90_ORIGINAL_LAO_STATUS'
        ,p_item_sequence=>110
        ,p_item_plug_id=>wwv_flow_api.id(5239055396194975)
        ,p_prompt=>'Set Lao Status'
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>4000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_field_alignment=>'LEFT-CENTER'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(14540224274129604)
        ,p_name=>'enable_fields_on_submit'
        ,p_event_sequence=>10
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'apexbeforepagesubmit'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(14540530780129606)
        ,p_event_id=>wwv_flow_api.id(14540224274129604)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P90_LAO_STATUS,P90_CT_AREA_EST_CODE,P90_DIVISION_CODE,P90_TEAM_CODE'
        ,p_attribute_01=>'unsetreadonly(this.affectedElements);'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(1978429331293447)
        ,p_name=>'MakeReadonlyItems'
        ,p_event_sequence=>20
        ,p_triggering_element_type=>'ITEM'
        ,p_triggering_element=>'P90_LAO_STATUS'
        ,p_triggering_condition_type=>'EQUALS'
        ,p_triggering_expression=>'N'
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'change'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(1978710683293447)
        ,p_event_id=>wwv_flow_api.id(1978429331293447)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P90_CT_AREA_EST_CODE,P90_DIVISION_CODE,P90_TEAM_CODE'
        ,p_attribute_01=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'setreadonly(this.affectedElements);',
            'setShuttleReadonly(''OFF030_USERS'');'))
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(1980421637385805)
        ,p_event_id=>wwv_flow_api.id(1978429331293447)
        ,p_event_result=>'FALSE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_affected_elements_type=>'ITEM'
        ,p_affected_elements=>'P90_CT_AREA_EST_CODE,P90_DIVISION_CODE,P90_TEAM_CODE'
        ,p_attribute_01=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'unsetreadonly(this.affectedElements);',
            'unsetShuttleReadonly(''OFF030_USERS'');'))
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_da_event(
            p_id=>wwv_flow_api.id(6613416923664669)
        ,p_name=>'Refresh shuttle'
        ,p_event_sequence=>30
        ,p_triggering_element_type=>'ITEM'
        ,p_triggering_element=>'P90_TEAM_CODE'
        ,p_bind_type=>'bind'
        ,p_bind_event_type=>'change'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(6613932507669200)
        ,p_event_id=>wwv_flow_api.id(6613416923664669)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>5
        ,p_execute_on_page_init=>'N'
        ,p_action=>'NATIVE_EXECUTE_PLSQL_CODE'
        ,p_attribute_01=>'mod_off030_pkg.p90_populate_user_shuttle2;'
        ,p_attribute_02=>'P90_CT_AREA_EST_CODE,P90_DIVISION_CODE,P90_TEAM_CODE'
        ,p_stop_execution_on_error=>'Y'
        ,p_wait_for_result=>'Y'
        );
    wwv_flow_api.create_page_da_action(
            p_id=>wwv_flow_api.id(6613726472664669)
        ,p_event_id=>wwv_flow_api.id(6613416923664669)
        ,p_event_result=>'TRUE'
        ,p_action_sequence=>10
        ,p_execute_on_page_init=>'Y'
        ,p_action=>'NATIVE_JAVASCRIPT_CODE'
        ,p_attribute_01=>'refreshShuttle(''OFF030_USERS'');'
        ,p_stop_execution_on_error=>'Y'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(1895219842950854)
        ,p_process_sequence=>70
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P90_Save_LAO_Status'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'mod_off030_pkg.p90_save;',
            ':P10_INIT := ''N'';'))
        ,p_process_when_button_id=>wwv_flow_api.id(1894600079927218)
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(8692027359344476)
        ,p_process_sequence=>80
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_SESSION_STATE'
        ,p_process_name=>'Clear Cache'
        ,p_attribute_01=>'CLEAR_CACHE_CURRENT_PAGE'
        ,p_process_when=>':REQUEST IN (''P90_BT_SAVE'',''P90_BT_CLOSE'')'
        ,p_process_when_type=>'PLSQL_EXPRESSION'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(8542508755295245)
        ,p_process_sequence=>10
        ,p_process_point=>'BEFORE_HEADER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P90_OLBH'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'select NVL(limited_access_offender,''N'') into :P90_LAO_STATUS from offender_vw where offender_pk = app_ctx_pkg.get (app_standard_context_pkg.gc_offender_pk);',
            '',
            ':P90_ORIGINAL_LAO_STATUS := :P90_LAO_STATUS;',
            ':P90_CT_AREA_EST_CODE    := app_ctx_pkg.get (app_standard_context_pkg.gc_ct_area_est_code);',
            ':P90_DIVISION_CODE       := app_ctx_pkg.get (app_standard_context_pkg.gc_division_code);',
            ':P90_TEAM_CODE           := app_ctx_pkg.get (app_standard_context_pkg.gc_user_team_code);'))
        ,p_process_is_stateful_y_n=>'Y'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(1881614066565837)
        ,p_process_sequence=>60
        ,p_process_point=>'BEFORE_HEADER'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P90_OLBH_INIT_COLL'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'mod_off030_pkg.p90_init_lao_coll;',
            'mod_off030_pkg.p90_populate_user_shuttle;',
            '',
            ':P90_INIT := ''N'';'))
        ,p_process_when=>':REQUEST NOT IN (''Add'',''Remove'') OR NVL(:P90_INIT,''Y'') = ''Y'''
        ,p_process_when_type=>'PLSQL_EXPRESSION'
        );
end;
/
prompt --application/pages/page_00500
begin
    wwv_flow_api.create_page(
            p_id=>500
        ,p_user_interface_id=>wwv_flow_api.id(74312104640072)
        ,p_name=>'LOCK ASSESSMENT '
        ,p_page_mode=>'NORMAL'
        ,p_step_title=>'Lock Assessment'
        ,p_step_sub_title_type=>'TEXT_WITH_SUBSTITUTIONS'
        ,p_first_item=>'NO_FIRST_ITEM'
        ,p_autocomplete_on_off=>'OFF'
        ,p_group_id=>wwv_flow_api.id(6767457294623073)
        ,p_page_template_options=>'#DEFAULT#'
        ,p_required_role=>wwv_flow_api.id(5052631587350853)
        ,p_dialog_chained=>'Y'
        ,p_overwrite_navigation_list=>'N'
        ,p_nav_list_template_options=>'#DEFAULT#'
        ,p_page_is_public_y_n=>'N'
        ,p_protection_level=>'D'
        ,p_cache_mode=>'NOCACHE'
        ,p_cache_timeout_seconds=>21600
        ,p_last_updated_by=>'ADAMWEIN'
        ,p_last_upd_yyyymmddhh24miss=>'20120326151605'
        );
    wwv_flow_api.create_report_region(
            p_id=>wwv_flow_api.id(6933214435068240)
        ,p_name=>'INCOMPLETE TASKS REPORT'
        ,p_template=>wwv_flow_api.id(20790622026320563)
        ,p_display_sequence=>2
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_new_grid_row=>false
        ,p_new_grid_column=>false
        ,p_display_column=>1
        ,p_display_point=>'BODY_3'
        ,p_source=>'return /* select 1 from dual */ mod_off030_pkg.p500_incomp_tasks_query;'
        ,p_source_type=>'NATIVE_FNC_REPORT'
        ,p_ajax_enabled=>'Y'
        ,p_fixed_header=>'NONE'
        ,p_query_row_template=>wwv_flow_api.id(20794602676320568)
        ,p_query_num_rows=>500
        ,p_query_options=>'DERIVED_REPORT_COLUMNS'
        ,p_query_show_nulls_as=>' - '
        ,p_query_break_cols=>'0'
        ,p_query_num_rows_type=>'0'
        ,p_query_row_count_max=>500
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_csv_output=>'N'
        ,p_prn_output=>'N'
        ,p_sort_null=>'F'
        ,p_query_asc_image_attr=>'width="13" height="12" '
        ,p_query_desc_image_attr=>'width="13" height="12" '
        ,p_plug_query_strip_html=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(6933502989068250)
        ,p_query_column_id=>1
        ,p_column_alias=>'OASYS_SET_PK'
        ,p_column_display_sequence=>1
        ,p_column_heading=>'Oasys Set Pk'
        ,p_disable_sort_column=>'N'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(6933631643068250)
        ,p_query_column_id=>2
        ,p_column_alias=>'LOCKED_IND'
        ,p_column_display_sequence=>2
        ,p_column_heading=>'Locked Ind'
        ,p_disable_sort_column=>'N'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(6933702154068250)
        ,p_query_column_id=>3
        ,p_column_alias=>'REF_SECTION_CODE'
        ,p_column_display_sequence=>3
        ,p_column_heading=>'Ref Section Code'
        ,p_default_sort_column_sequence=>1
        ,p_disable_sort_column=>'N'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(6933820110068250)
        ,p_query_column_id=>4
        ,p_column_alias=>'SECTION_NAME'
        ,p_column_display_sequence=>4
        ,p_column_heading=>'Incomplete Sections'
        ,p_disable_sort_column=>'N'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(6933921864068250)
        ,p_query_column_id=>5
        ,p_column_alias=>'SECTION_STATUS_CAT'
        ,p_column_display_sequence=>5
        ,p_column_heading=>'Section Status Cat'
        ,p_disable_sort_column=>'N'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_report_columns(
            p_id=>wwv_flow_api.id(6934007156068250)
        ,p_query_column_id=>6
        ,p_column_alias=>'SECTION_STATUS_ELM'
        ,p_column_display_sequence=>6
        ,p_column_heading=>'Section Status Elm'
        ,p_disable_sort_column=>'N'
        ,p_hidden_column=>'Y'
        ,p_lov_show_nulls=>'NO'
        ,p_lov_display_extra=>'YES'
        ,p_include_in_export=>'Y'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(6986908068807381)
        ,p_plug_name=>'Lock Assessment Above'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(20790622026320563)
        ,p_plug_display_sequence=>1
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_plug(
            p_id=>wwv_flow_api.id(7140015009479292)
        ,p_plug_name=>'Lock Assessment Below'
        ,p_region_template_options=>'#DEFAULT#'
        ,p_component_template_options=>'#DEFAULT#'
        ,p_plug_template=>wwv_flow_api.id(20790622026320563)
        ,p_plug_display_sequence=>12
        ,p_include_in_reg_disp_sel_yn=>'N'
        ,p_plug_new_grid_row=>false
        ,p_plug_new_grid_column=>false
        ,p_plug_display_column=>1
        ,p_plug_display_point=>'BODY_3'
        ,p_plug_query_row_template=>1
        ,p_plug_query_headings_type=>'QUERY_COLUMNS'
        ,p_plug_query_num_rows=>15
        ,p_plug_query_num_rows_type=>'NEXT_PREVIOUS_LINKS'
        ,p_plug_query_show_nulls_as=>' - '
        ,p_pagination_display_position=>'BOTTOM_RIGHT'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'HTML'
        ,p_attribute_03=>'N'
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(6989014180875388)
        ,p_button_sequence=>4
        ,p_button_plug_id=>wwv_flow_api.id(7140015009479292)
        ,p_button_name=>'P500_LOCK_ASSESSMENT'
        ,p_button_static_id=>'P500_LOCK_ASSESSMENT'
        ,p_button_action=>'SUBMIT'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'LOCK INCOMPLETE'
        ,p_button_position=>'BODY'
        ,p_button_alignment=>'LEFT'
        ,p_request_source=>'LOCK'
        ,p_request_source_type=>'STATIC'
        ,p_grid_new_grid=>false
        ,p_grid_new_row=>'N'
        ,p_grid_new_column=>'Y'
        ,p_grid_column_span=>1
        ,p_grid_row_span=>1
        );
    wwv_flow_api.create_page_button(
            p_id=>wwv_flow_api.id(6988432703842880)
        ,p_button_sequence=>10
        ,p_button_plug_id=>wwv_flow_api.id(13368946808533955)
        ,p_button_name=>'P10_BT_CLOSE'
        ,p_button_action=>'REDIRECT_URL'
        ,p_button_template_options=>'#DEFAULT#'
        ,p_button_template_id=>wwv_flow_api.id(20790115155320563)
        ,p_button_image_alt=>'Close'
        ,p_button_position=>'REGION_TEMPLATE_NEXT'
        ,p_button_redirect_url=>'javascript:&AI_NAV_SYS.(''PAGE_10::'');'
        ,p_grid_new_grid=>false
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(8512410664951659)
        ,p_branch_action=>'10'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_STEP'
        ,p_branch_sequence=>10
        ,p_branch_condition_type=>'PLSQL_EXPRESSION'
        ,p_branch_condition=>':REQUEST = ''LOCK'''
        ,p_branch_comment=>'Created 13-MAY-2010 09:50 by ZIPPYMICHAEL'
        );
    wwv_flow_api.create_page_branch(
            p_id=>wwv_flow_api.id(8511526201927819)
        ,p_branch_action=>'return app_navigation_pkg.navurl;'
        ,p_branch_point=>'AFTER_PROCESSING'
        ,p_branch_type=>'BRANCH_TO_FUNCTION_RETURNING_URL'
        ,p_branch_sequence=>9999
        ,p_branch_comment=>'Created 13-MAY-2010 09:46 by ZIPPYMICHAEL'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6987614217828124)
        ,p_name=>'P500_OASYS_SET_PK'
        ,p_item_sequence=>2
        ,p_item_plug_id=>wwv_flow_api.id(6986908068807381)
        ,p_display_as=>'NATIVE_HIDDEN'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>1
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_begin_on_new_line=>'N'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(6988720282867660)
        ,p_name=>'P500_MESSAGE'
        ,p_item_sequence=>3
        ,p_item_plug_id=>wwv_flow_api.id(7140015009479292)
        ,p_prompt=>'Do You Wish To Lock The Assessment?       '
        ,p_display_as=>'NATIVE_DISPLAY_ONLY'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'BELOW'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_attribute_01=>'Y'
        ,p_attribute_02=>'VALUE'
        ,p_attribute_04=>'N'
        );
    wwv_flow_api.create_page_item(
            p_id=>wwv_flow_api.id(7116523405462760)
        ,p_name=>'P500_TEXT'
        ,p_item_sequence=>1
        ,p_item_plug_id=>wwv_flow_api.id(6986908068807381)
        ,p_prompt=>'The Following Sections are Incomplete;-'
        ,p_display_as=>'NATIVE_DISPLAY_ONLY'
        ,p_cSize=>30
        ,p_cMaxlength=>2000
        ,p_cHeight=>5
        ,p_cAttributes=>'style="white-space:nowrap"'
        ,p_colspan=>1
        ,p_rowspan=>1
        ,p_label_alignment=>'RIGHT'
        ,p_field_template=>wwv_flow_api.id(20795102685320569)
        ,p_item_template_options=>'#DEFAULT#'
        ,p_lov_display_extra=>'NO'
        ,p_escape_on_http_output=>'N'
        ,p_attribute_01=>'N'
        ,p_attribute_02=>'VALUE'
        ,p_attribute_04=>'N'
        );
    wwv_flow_api.create_page_process(
            p_id=>wwv_flow_api.id(7099910732194193)
        ,p_process_sequence=>10
        ,p_process_point=>'AFTER_SUBMIT'
        ,p_process_type=>'NATIVE_PLSQL'
        ,p_process_name=>'P500_OSA_LOCK_ASS_PROC'
        ,p_process_sql_clob=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
            'BEGIN',
            '',
            '    -- lock the assessment incomplete',
            '',
            '    mod_off030_pkg.lock_incomplete',
            '    ( p_offender_pk   => :P10_OFFENDER_PK',
            '    , p_oasys_set_pk  => :P500_OASYS_SET_PK',
            '    );',
            '',
            '   EXCEPTION WHEN OTHERS ',
            '   THEN',
            '     elog_api.ins;',
            '     RAISE;    ',
            '',
            'END;'))
        ,p_process_when=>'RETURN (:REQUEST = ''LOCK'');'
        ,p_process_when_type=>'FUNCTION_BODY'
        );
end;
/
prompt --application/deployment/definition
begin
    wwv_flow_api.create_install(
            p_id=>wwv_flow_api.id(7949211051782481)
        );
end;
/
prompt --application/deployment/install
begin
    null;
end;
/
prompt --application/deployment/checks
begin
    null;
end;
/
prompt --application/deployment/buildoptions
begin
    null;
end;
/
prompt --application/end_environment
begin
    wwv_flow_api.import_end(p_auto_install_sup_obj => nvl(wwv_flow_application_install.get_auto_install_sup_obj, false));
    commit;
end;
/
set verify on feedback on define on
    prompt  ...done
