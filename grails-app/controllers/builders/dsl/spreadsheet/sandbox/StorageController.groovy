package builders.dsl.spreadsheet.sandbox

class StorageController {

    def download(String id) {
        render file: Storage.read(id), contentType: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', fileName: "${id}.xlsx"
    }
}
