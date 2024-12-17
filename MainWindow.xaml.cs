using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Microsoft.UI;
using Microsoft.UI.Composition.SystemBackdrops;
using Microsoft.UI.Windowing;
using Microsoft.UI.Xaml;
using Microsoft.UI.Xaml.Controls;
using Microsoft.UI.Xaml.Controls.Primitives;
using Microsoft.UI.Xaml.Data;
using Microsoft.UI.Xaml.Input;
using Microsoft.UI.Xaml.Media;
using Microsoft.UI.Xaml.Navigation;
using Receptacle.Models;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.Graphics.Printing.OptionDetails;

namespace Receptacle {
    public sealed partial class MainWindow : Window {

        private IList<MediaItem> _items { get; set; }
        private bool _isLoaded = false;

        public MainWindow() {
            this.InitializeComponent();
            ExtendsContentIntoTitleBar = true;
            this.AppWindow.TitleBar.PreferredHeightOption = TitleBarHeightOption.Standard;
            ItemList.Loaded += ItemList_Loaded;
        }

        public void PopulateData() {
            if (_isLoaded) return;
            _isLoaded = true;

            MediaItem cd = new MediaItem {
                Id = 1,
                Name = "Classical Favorites",
                MediaType = Enums.ItemType.Music,
                MediumInfo = new Medium {
                    Id = 1, 
                    MediaType = Enums.ItemType.Music,
                    Name = "CD"
                }, 
            };

            MediaItem book = new MediaItem {
                Id = 2,
                Name = "Classic Fairy Tales",
                MediaType = Enums.ItemType.Book,
                MediumInfo = new Medium {
                    Id = 2, 
                    MediaType = Enums.ItemType.Book,
                    Name = "Book"
                }, 
            };

            MediaItem bluRay= new MediaItem {
                Id = 3,
                Name = "The Terminator",
                MediaType = Enums.ItemType.Video,
                MediumInfo = new Medium {
                    Id = 2, 
                    MediaType = Enums.ItemType.Video,
                    Name = "BluRay"
                }, 
            };

            _items = new List<MediaItem> {
                cd, book, bluRay
            };
        }

        private void ItemList_Loaded(object sender, RoutedEventArgs e) {
            var listView = (ListView)sender;
            this.PopulateData();
            listView.ItemsSource = _items;
        }
    }
}
