using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Receptacle.Models;

namespace Receptacle.ViewModels {
    class MainViewModel : BindableBase {

        private string selectedMedium;
        private ObservableCollection<MediaItem> items;
        private ObservableCollection<MediaItem> allitems;
        private IList<string> mediums;

        public MainViewModel() {
            PopulateDate();
        }

        public void PopulateDate() { }

        public IList<string> Mediums {
            get { return this.mediums; }
            set { SetProperty(ref this.mediums, value); }
        }
    }
}
