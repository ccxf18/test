import {Tabbar,NoticeBar,Popover, Checkbox,CheckboxGroup, DatetimePicker,Popup,Swipe, SwipeItem,TabbarItem,Overlay,Dialog, Button,Picker, Cell, CellGroup, Loading, Uploader, Toast, image, RadioGroup, Radio, NavBar, Icon, Tab, Tabs, List,Form, Field} from 'vant'

const vant = {
    install: function(Vue) {
      Vue.use(NoticeBar)
      Vue.use(Popover)
      Vue.use(Swipe)
      Vue.use(CheckboxGroup)
      Vue.use(Checkbox)
      Vue.use(SwipeItem)
      Vue.use(Overlay)
      Vue.use(Button)
      Vue.use(Loading)
      Vue.use(Uploader)
      Vue.use(Toast)
      Vue.use(image)
      Vue.use(Tabbar)
      Vue.use(TabbarItem)
      Vue.use(NavBar)
      Vue.use(Icon)
      Vue.use(Tab)
      Vue.use(Tabs)
      Vue.use(List)
      Vue.use(Form)
      Vue.use(Field)
      Vue.use(CellGroup)
      Vue.use(Cell)
      Vue.use(RadioGroup)
      Vue.use(Radio)
      Vue.use(Picker)
      Vue.use(DatetimePicker)
      Vue.use(Popup)
      Vue.use(Dialog)
    }
  }
   
  export default vant
