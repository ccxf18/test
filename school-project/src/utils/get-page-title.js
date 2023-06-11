import defaultSettings from '@/settings'

const title = defaultSettings.title || '云农门户智慧大棚子系统'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
