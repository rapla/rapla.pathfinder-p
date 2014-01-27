$wnd.com_pathfinder_util_widgetset_PathfinderWidgetset.runAsyncCallback7("function $getCellForEvent(this$static, event_0){\n  var column, row, td;\n  td = $getEventTargetCell(this$static, event_0.nativeEvent);\n  if (!td) {\n    return null;\n  }\n  row = $getParentElement(td).sectionRowIndex;\n  column = td.cellIndex;\n  return new HTMLTable$Cell_0(row, column);\n}\n\nfunction $getEventTargetCell(this$static, event_0){\n  var body_0, td, tr;\n  td = ($clinit_DOM() , event_0.target);\n  for (; td; td = $getParentElement(td)) {\n    if ($equalsIgnoreCase($getPropertyString(td, 'tagName'), 'td')) {\n      tr = $getParentElement(td);\n      body_0 = $getParentElement(tr);\n      if (body_0 == this$static.bodyElem) {\n        return td;\n      }\n    }\n    if (td == this$static.bodyElem) {\n      return null;\n    }\n  }\n  return null;\n}\n\nfunction HTMLTable$Cell_0(rowIndex, cellIndex){\n  this.cellIndex_0 = cellIndex;\n  this.rowIndex = rowIndex;\n}\n\ndefineSeed(479, 1, {}, HTMLTable$Cell_0);\n_.cellIndex_0 = 0;\n_.rowIndex = 0;\nfunction $select(this$static, p0, p1){\n  $invoke(this$static.val$handler, new Method_0(new Type_0(Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridServerRpc_2_classLit), 'select'), initValues(_3Ljava_lang_Object_2_classLit, makeCastMap([Q$Serializable, Q$Object_$1]), Q$Object, [valueOf_41(p0), valueOf_41(p1)]));\n}\n\nfunction $load_5(this$static){\n  $setClass(this$static.val$store, 'com.vaadin.ui.components.colorpicker.ColorPickerGrid', Lcom_vaadin_client_ui_colorpicker_ColorPickerGridConnector_2_classLit);\n  $setInvoker(this$static.val$store, Lcom_vaadin_client_ui_colorpicker_ColorPickerGridConnector_2_classLit, '!new', new ConnectorBundleLoaderImpl$8$1$1_0);\n  $setInvoker(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, '!new', new ConnectorBundleLoaderImpl$8$1$2_0);\n  $setReturnType(this$static.val$store, Lcom_vaadin_client_ui_colorpicker_ColorPickerGridConnector_2_classLit, 'getState', new Type_0(Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit));\n  $setProperties(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, initValues(_3Ljava_lang_String_2_classLit, makeCastMap([Q$Serializable, Q$Object_$1, Q$String_$1]), Q$String, ['description', 'caption', 'errorMessage', 'changedY', 'changedX', 'primaryStyleName', 'width', 'readOnly', 'rowCount', 'immediate', 'enabled', 'height', 'registeredEventListeners', 'styles', 'columnCount', 'changedColor', 'resources', 'id']));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'description', new Type_0(Ljava_lang_String_2_classLit));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'caption', new Type_0(Ljava_lang_String_2_classLit));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'errorMessage', new Type_0(Ljava_lang_String_2_classLit));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'changedY', new Type_0(_3Ljava_lang_String_2_classLit));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'changedX', new Type_0(_3Ljava_lang_String_2_classLit));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'primaryStyleName', new Type_0(Ljava_lang_String_2_classLit));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'width', new Type_0(Ljava_lang_String_2_classLit));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'readOnly', new Type_0(Ljava_lang_Boolean_2_classLit));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'rowCount', new Type_0(Ljava_lang_Integer_2_classLit));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'immediate', new Type_0(Ljava_lang_Boolean_2_classLit));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'enabled', new Type_0(Ljava_lang_Boolean_2_classLit));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'height', new Type_0(Ljava_lang_String_2_classLit));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'registeredEventListeners', new Type_1('java.util.Set', initValues(_3Lcom_vaadin_client_metadata_Type_2_classLit, makeCastMap([Q$Type_$1, Q$Serializable, Q$Object_$1]), Q$Type, [new Type_0(Ljava_lang_String_2_classLit)])));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'styles', new Type_1('java.util.List', initValues(_3Lcom_vaadin_client_metadata_Type_2_classLit, makeCastMap([Q$Type_$1, Q$Serializable, Q$Object_$1]), Q$Type, [new Type_0(Ljava_lang_String_2_classLit)])));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'changedColor', new Type_0(_3Ljava_lang_String_2_classLit));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'columnCount', new Type_0(Ljava_lang_Integer_2_classLit));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'resources', new Type_1('java.util.Map', initValues(_3Lcom_vaadin_client_metadata_Type_2_classLit, makeCastMap([Q$Type_$1, Q$Serializable, Q$Object_$1]), Q$Type, [new Type_0(Ljava_lang_String_2_classLit), new Type_0(Lcom_vaadin_shared_communication_URLReference_2_classLit)])));\n  $setPropertyType(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'id', new Type_0(Ljava_lang_String_2_classLit));\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'description', new ConnectorBundleLoaderImpl$8$1$3_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'caption', new ConnectorBundleLoaderImpl$8$1$4_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'errorMessage', new ConnectorBundleLoaderImpl$8$1$5_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'changedY', new ConnectorBundleLoaderImpl$8$1$6_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'changedX', new ConnectorBundleLoaderImpl$8$1$7_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'primaryStyleName', new ConnectorBundleLoaderImpl$8$1$8_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'width', new ConnectorBundleLoaderImpl$8$1$9_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'readOnly', new ConnectorBundleLoaderImpl$8$1$10_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'rowCount', new ConnectorBundleLoaderImpl$8$1$11_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'immediate', new ConnectorBundleLoaderImpl$8$1$12_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'enabled', new ConnectorBundleLoaderImpl$8$1$13_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'height', new ConnectorBundleLoaderImpl$8$1$14_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'registeredEventListeners', new ConnectorBundleLoaderImpl$8$1$15_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'styles', new ConnectorBundleLoaderImpl$8$1$16_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'changedColor', new ConnectorBundleLoaderImpl$8$1$17_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'columnCount', new ConnectorBundleLoaderImpl$8$1$18_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'resources', new ConnectorBundleLoaderImpl$8$1$19_0);\n  $setSetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'id', new ConnectorBundleLoaderImpl$8$1$20_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'description', new ConnectorBundleLoaderImpl$8$1$21_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'caption', new ConnectorBundleLoaderImpl$8$1$22_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'errorMessage', new ConnectorBundleLoaderImpl$8$1$23_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'changedY', new ConnectorBundleLoaderImpl$8$1$24_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'changedX', new ConnectorBundleLoaderImpl$8$1$25_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'primaryStyleName', new ConnectorBundleLoaderImpl$8$1$26_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'width', new ConnectorBundleLoaderImpl$8$1$27_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'readOnly', new ConnectorBundleLoaderImpl$8$1$28_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'rowCount', new ConnectorBundleLoaderImpl$8$1$29_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'immediate', new ConnectorBundleLoaderImpl$8$1$30_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'enabled', new ConnectorBundleLoaderImpl$8$1$31_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'height', new ConnectorBundleLoaderImpl$8$1$32_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'registeredEventListeners', new ConnectorBundleLoaderImpl$8$1$33_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'styles', new ConnectorBundleLoaderImpl$8$1$34_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'changedColor', new ConnectorBundleLoaderImpl$8$1$35_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'columnCount', new ConnectorBundleLoaderImpl$8$1$36_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'resources', new ConnectorBundleLoaderImpl$8$1$37_0);\n  $setGetter(this$static.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'id', new ConnectorBundleLoaderImpl$8$1$38_0);\n}\n\ndefineSeed(3091, 1, makeCastMap([Q$RunAsyncCallback]));\n_.onSuccess_1 = function onSuccess_7(){\n  $load_5(this);\n  $setLoaded_0((!impl_7 && (impl_7 = new ConnectorBundleLoaderImpl_0) , impl_7), this.this$1.packageName);\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$1_0(){\n}\n\ndefineSeed(3092, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$1_0);\n_.invoke = function invoke_2261(target, params){\n  return new ColorPickerGridConnector_0;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$10_0(){\n}\n\ndefineSeed(3093, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$10_0);\n_.invoke = function invoke_2262(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).readOnly = dynamicCast(params[0], Q$Boolean).value_0;\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$11_0(){\n}\n\ndefineSeed(3094, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$11_0);\n_.invoke = function invoke_2263(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).rowCount = dynamicCast(params[0], Q$Integer).value_0;\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$12_0(){\n}\n\ndefineSeed(3095, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$12_0);\n_.invoke = function invoke_2264(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).immediate = dynamicCast(params[0], Q$Boolean).value_0;\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$13_0(){\n}\n\ndefineSeed(3096, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$13_0);\n_.invoke = function invoke_2265(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).enabled = dynamicCast(params[0], Q$Boolean).value_0;\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$14_0(){\n}\n\ndefineSeed(3097, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$14_0);\n_.invoke = function invoke_2266(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).height_0 = dynamicCast(params[0], Q$String);\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$15_0(){\n}\n\ndefineSeed(3098, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$15_0);\n_.invoke = function invoke_2267(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).registeredEventListeners = dynamicCast(params[0], Q$Set);\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$16_0(){\n}\n\ndefineSeed(3099, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$16_0);\n_.invoke = function invoke_2268(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).styles = dynamicCast(params[0], Q$List);\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$17_0(){\n}\n\ndefineSeed(3100, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$17_0);\n_.invoke = function invoke_2269(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).changedColor = dynamicCast(params[0], Q$String_$1);\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$18_0(){\n}\n\ndefineSeed(3101, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$18_0);\n_.invoke = function invoke_2270(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).columnCount = dynamicCast(params[0], Q$Integer).value_0;\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$19_0(){\n}\n\ndefineSeed(3102, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$19_0);\n_.invoke = function invoke_2271(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).resources = dynamicCast(params[0], Q$Map);\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$2_0(){\n}\n\ndefineSeed(3103, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$2_0);\n_.invoke = function invoke_2272(target, params){\n  return new ColorPickerGridState_0;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$20_0(){\n}\n\ndefineSeed(3104, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$20_0);\n_.invoke = function invoke_2273(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).id_0 = dynamicCast(params[0], Q$String);\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$21_0(){\n}\n\ndefineSeed(3105, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$21_0);\n_.invoke = function invoke_2274(bean, params){\n  return dynamicCast(bean, Q$ColorPickerGridState).description;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$22_0(){\n}\n\ndefineSeed(3106, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$22_0);\n_.invoke = function invoke_2275(bean, params){\n  return dynamicCast(bean, Q$ColorPickerGridState).caption_0;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$23_0(){\n}\n\ndefineSeed(3107, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$23_0);\n_.invoke = function invoke_2276(bean, params){\n  return dynamicCast(bean, Q$ColorPickerGridState).errorMessage;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$24_0(){\n}\n\ndefineSeed(3108, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$24_0);\n_.invoke = function invoke_2277(bean, params){\n  return dynamicCast(bean, Q$ColorPickerGridState).changedY;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$25_0(){\n}\n\ndefineSeed(3109, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$25_0);\n_.invoke = function invoke_2278(bean, params){\n  return dynamicCast(bean, Q$ColorPickerGridState).changedX;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$26_0(){\n}\n\ndefineSeed(3110, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$26_0);\n_.invoke = function invoke_2279(bean, params){\n  return dynamicCast(bean, Q$ColorPickerGridState).primaryStyleName;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$27_0(){\n}\n\ndefineSeed(3111, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$27_0);\n_.invoke = function invoke_2280(bean, params){\n  return dynamicCast(bean, Q$ColorPickerGridState).width_0;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$28_0(){\n}\n\ndefineSeed(3112, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$28_0);\n_.invoke = function invoke_2281(bean, params){\n  return $clinit_Boolean() , dynamicCast(bean, Q$ColorPickerGridState).readOnly?TRUE_4:FALSE_4;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$29_0(){\n}\n\ndefineSeed(3113, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$29_0);\n_.invoke = function invoke_2282(bean, params){\n  return valueOf_41(dynamicCast(bean, Q$ColorPickerGridState).rowCount);\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$3_0(){\n}\n\ndefineSeed(3114, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$3_0);\n_.invoke = function invoke_2283(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).description = dynamicCast(params[0], Q$String);\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$30_0(){\n}\n\ndefineSeed(3115, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$30_0);\n_.invoke = function invoke_2284(bean, params){\n  return $clinit_Boolean() , dynamicCast(bean, Q$ColorPickerGridState).immediate?TRUE_4:FALSE_4;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$31_0(){\n}\n\ndefineSeed(3116, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$31_0);\n_.invoke = function invoke_2285(bean, params){\n  return $clinit_Boolean() , dynamicCast(bean, Q$ColorPickerGridState).enabled?TRUE_4:FALSE_4;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$32_0(){\n}\n\ndefineSeed(3117, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$32_0);\n_.invoke = function invoke_2286(bean, params){\n  return dynamicCast(bean, Q$ColorPickerGridState).height_0;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$33_0(){\n}\n\ndefineSeed(3118, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$33_0);\n_.invoke = function invoke_2287(bean, params){\n  return dynamicCast(bean, Q$ColorPickerGridState).registeredEventListeners;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$34_0(){\n}\n\ndefineSeed(3119, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$34_0);\n_.invoke = function invoke_2288(bean, params){\n  return dynamicCast(bean, Q$ColorPickerGridState).styles;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$35_0(){\n}\n\ndefineSeed(3120, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$35_0);\n_.invoke = function invoke_2289(bean, params){\n  return dynamicCast(bean, Q$ColorPickerGridState).changedColor;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$36_0(){\n}\n\ndefineSeed(3121, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$36_0);\n_.invoke = function invoke_2290(bean, params){\n  return valueOf_41(dynamicCast(bean, Q$ColorPickerGridState).columnCount);\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$37_0(){\n}\n\ndefineSeed(3122, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$37_0);\n_.invoke = function invoke_2291(bean, params){\n  return dynamicCast(bean, Q$ColorPickerGridState).resources;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$38_0(){\n}\n\ndefineSeed(3123, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$38_0);\n_.invoke = function invoke_2292(bean, params){\n  return dynamicCast(bean, Q$ColorPickerGridState).id_0;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$4_0(){\n}\n\ndefineSeed(3124, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$4_0);\n_.invoke = function invoke_2293(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).caption_0 = dynamicCast(params[0], Q$String);\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$5_0(){\n}\n\ndefineSeed(3125, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$5_0);\n_.invoke = function invoke_2294(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).errorMessage = dynamicCast(params[0], Q$String);\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$6_0(){\n}\n\ndefineSeed(3126, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$6_0);\n_.invoke = function invoke_2295(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).changedY = dynamicCast(params[0], Q$String_$1);\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$7_0(){\n}\n\ndefineSeed(3127, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$7_0);\n_.invoke = function invoke_2296(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).changedX = dynamicCast(params[0], Q$String_$1);\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$8_0(){\n}\n\ndefineSeed(3128, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$8_0);\n_.invoke = function invoke_2297(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).primaryStyleName = dynamicCast(params[0], Q$String);\n  return null;\n}\n;\nfunction ConnectorBundleLoaderImpl$8$1$9_0(){\n}\n\ndefineSeed(3129, 1, makeCastMap([Q$Invoker]), ConnectorBundleLoaderImpl$8$1$9_0);\n_.invoke = function invoke_2298(bean, params){\n  dynamicCast(bean, Q$ColorPickerGridState).width_0 = dynamicCast(params[0], Q$String);\n  return null;\n}\n;\nfunction ColorPickerGridConnector_0(){\n  AbstractComponentConnector_0.call(this);\n  this.rpc = dynamicCast(create_5(Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridServerRpc_2_classLit, this), Q$ColorPickerGridServerRpc);\n}\n\ndefineSeed(3460, 553, makeCastMap([Q$ClickHandler, Q$EventHandler, Q$ComponentConnector, Q$ServerConnector, Q$StateChangeEvent$StateChangeHandler, Q$AbstractConnector, Q$Connector, Q$Serializable]), ColorPickerGridConnector_0);\n_.createWidget = function createWidget_5(){\n  return new VColorPickerGrid_0;\n}\n;\n_.getState_0 = function getState_35(){\n  return !this.state && (this.state = $createState(this)) , dynamicCast(dynamicCast(this.state, Q$AbstractComponentState), Q$ColorPickerGridState);\n}\n;\n_.getState = function getState_36(){\n  return !this.state && (this.state = $createState(this)) , dynamicCast(dynamicCast(this.state, Q$AbstractComponentState), Q$ColorPickerGridState);\n}\n;\n_.getWidget_0 = function getWidget_22(){\n  return dynamicCast($getWidget_1(this), Q$VColorPickerGrid);\n}\n;\n_.init = function init_12(){\n  $addDomHandler(dynamicCast($getWidget_1(this), Q$VColorPickerGrid), this, ($clinit_ClickEvent() , $clinit_ClickEvent() , TYPE_1));\n}\n;\n_.onClick = function onClick_48(event_0){\n  $select(this.rpc, dynamicCast($getWidget_1(this), Q$VColorPickerGrid).selectedX, dynamicCast($getWidget_1(this), Q$VColorPickerGrid).selectedY);\n}\n;\n_.onStateChanged = function onStateChanged_16(stateChangeEvent){\n  $onStateChanged(this, stateChangeEvent);\n  ($hasPropertyChanged(stateChangeEvent, 'rowCount') || $hasPropertyChanged(stateChangeEvent, 'columnCount') || $hasPropertyChanged(stateChangeEvent, 'updateGrid')) && $updateGrid(dynamicCast($getWidget_1(this), Q$VColorPickerGrid), (!this.state && (this.state = $createState(this)) , dynamicCast(dynamicCast(this.state, Q$AbstractComponentState), Q$ColorPickerGridState)).rowCount, (!this.state && (this.state = $createState(this)) , dynamicCast(dynamicCast(this.state, Q$AbstractComponentState), Q$ColorPickerGridState)).columnCount);\n  if ($hasPropertyChanged(stateChangeEvent, 'changedX') || $hasPropertyChanged(stateChangeEvent, 'changedY') || $hasPropertyChanged(stateChangeEvent, 'changedColor') || $hasPropertyChanged(stateChangeEvent, 'updateColor')) {\n    $updateColor(dynamicCast($getWidget_1(this), Q$VColorPickerGrid), (!this.state && (this.state = $createState(this)) , dynamicCast(dynamicCast(this.state, Q$AbstractComponentState), Q$ColorPickerGridState)).changedColor, (!this.state && (this.state = $createState(this)) , dynamicCast(dynamicCast(this.state, Q$AbstractComponentState), Q$ColorPickerGridState)).changedX, (!this.state && (this.state = $createState(this)) , dynamicCast(dynamicCast(this.state, Q$AbstractComponentState), Q$ColorPickerGridState)).changedY);\n    dynamicCast($getWidget_1(this), Q$VColorPickerGrid).gridLoaded || $invoke(this.rpc.val$handler, new Method_0(new Type_0(Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridServerRpc_2_classLit), 'refresh'), initValues(_3Ljava_lang_Object_2_classLit, makeCastMap([Q$Serializable, Q$Object_$1]), Q$Object, []));\n  }\n}\n;\nfunction $createGrid(this$static){\n  this$static.grid = new Grid_0(this$static.rows_0, this$static.columns);\n  $setWidth_0(this$static.grid, '100%');\n  $setHeight_0(this$static.grid, '100%');\n  $addDomHandler(this$static.grid, this$static, ($clinit_ClickEvent() , $clinit_ClickEvent() , TYPE_1));\n  return this$static.grid;\n}\n\nfunction $updateColor(this$static, changedColor, changedX, changedY){\n  var c, element;\n  if (changedColor != null && changedX != null && changedY != null) {\n    if (changedColor.length == changedX.length && changedX.length == changedY.length) {\n      for (c = 0; c < changedColor.length; ++c) {\n        element = $getElement(this$static.grid.cellFormatter, __parseAndValidateInt(changedX[c], -2147483648, 2147483647), __parseAndValidateInt(changedY[c], -2147483648, 2147483647));\n        $setPropertyImpl(element.style, 'background', changedColor[c]);\n      }\n    }\n    this$static.gridLoaded = true;\n  }\n}\n\nfunction $updateGrid(this$static, rowCount, columnCount){\n  this$static.rows_0 = rowCount;\n  this$static.columns = columnCount;\n  $remove_3(this$static, this$static.grid);\n  $add_2(this$static, $createGrid(this$static), 0, 0);\n}\n\nfunction VColorPickerGrid_0(){\n  AbsolutePanel_0.call(this);\n  this.rows_0 = 1;\n  this.columns = 1;\n  this.gridLoaded = false;\n  $add_2(this, $createGrid(this), 0, 0);\n}\n\ndefineSeed(3462, 437, makeCastMap([Q$ClickHandler, Q$HasClickHandlers, Q$HasAttachHandlers, Q$EventHandler, Q$HasHandlers, Q$EventListener, Q$HasVisibility, Q$HasWidgets, Q$HasWidgets$ForIsWidget, Q$IsWidget, Q$Panel, Q$UIObject, Q$Widget, Q$VColorPickerGrid, Q$Iterable]), VColorPickerGrid_0);\n_.addClickHandler = function addClickHandler_5(handler){\n  return $addDomHandler(this, handler, ($clinit_ClickEvent() , $clinit_ClickEvent() , TYPE_1));\n}\n;\n_.onClick = function onClick_49(event_0){\n  var cell;\n  cell = $getCellForEvent(this.grid, event_0);\n  if (!cell) {\n    return;\n  }\n  this.selectedY = cell.rowIndex;\n  this.selectedX = cell.cellIndex_0;\n}\n;\n_.columns = 0;\n_.gridLoaded = false;\n_.rows_0 = 0;\n_.selectedX = 0;\n_.selectedY = 0;\nfunction ColorPickerGridState_0(){\n  AbstractComponentState_0.call(this);\n}\n\ndefineSeed(3709, 558, makeCastMap([Q$AbstractComponentState, Q$SharedState, Q$ColorPickerGridState, Q$Serializable]), ColorPickerGridState_0);\n_.columnCount = 0;\n_.rowCount = 0;\nvar Lcom_vaadin_client_ui_colorpicker_ColorPickerGridConnector_2_classLit = createForClass('com.vaadin.client.ui.colorpicker.', 'ColorPickerGridConnector', 3460), Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit = createForClass('com.vaadin.shared.ui.colorpicker.', 'ColorPickerGridState', 3709), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$1_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$1', 3092), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$2_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$2', 3103), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$3_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$3', 3114), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$4_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$4', 3124), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$5_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$5', 3125), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$6_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$6', 3126), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$7_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$7', 3127), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$8_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$8', 3128), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$9_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$9', 3129), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$10_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$10', 3093), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$11_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$11', 3094), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$12_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$12', 3095), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$13_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$13', 3096), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$14_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$14', 3097), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$15_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$15', 3098), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$16_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$16', 3099), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$17_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$17', 3100), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$18_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$18', 3101), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$19_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$19', 3102), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$20_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$20', 3104), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$21_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$21', 3105), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$22_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$22', 3106), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$23_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$23', 3107), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$24_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$24', 3108), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$25_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$25', 3109), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$26_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$26', 3110), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$27_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$27', 3111), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$28_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$28', 3112), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$29_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$29', 3113), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$30_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$30', 3115), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$31_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$31', 3116), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$32_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$32', 3117), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$33_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$33', 3118), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$34_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$34', 3119), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$35_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$35', 3120), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$36_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$36', 3121), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$37_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$37', 3122), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$8$1$38_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$8$1$38', 3123), Lcom_google_gwt_user_client_ui_HTMLTable$Cell_2_classLit = createForClass('com.google.gwt.user.client.ui.', 'HTMLTable$Cell', 479), Lcom_vaadin_client_ui_colorpicker_VColorPickerGrid_2_classLit = createForClass('com.vaadin.client.ui.colorpicker.', 'VColorPickerGrid', 3462);\n$entry(onLoad)(7);\n\n//# sourceURL=com.pathfinder.util.widgetset.PathfinderWidgetset-7.js\n")