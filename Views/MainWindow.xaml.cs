using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.CompilerServices;
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
using Receptacle.Shared.Enums;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.Graphics.Printing.OptionDetails;

namespace Receptacle.Views {
    public sealed partial class MainWindow : Window {

        private IList<MediaItem> _items { get; set; }
        private IList<string> _mediums { get; set; }
        private IList<MediaItem> _allItems { get; set; }
        private bool _isLoaded = false;

        public MainWindow() {
            this.InitializeComponent();
            this.InitWindow();
        }

        public void InitWindow() {
            ExtendsContentIntoTitleBar = true;
            this.AppWindow.TitleBar.PreferredHeightOption = TitleBarHeightOption.Standard;

            ItemList.Loaded += ItemList_Loaded;
            ItemFilter.Loaded += ItemFilter_Loaded;
            // AddButton.Click += AddButton_Clicked;
        }

        public void PopulateData() {
            if (_isLoaded) return;
            _isLoaded = true;

            MediaItem cd = new MediaItem {
                Id = 1,
                Name = "Classical Favorites",
                MediaType = ItemType.Music,
                MediumInfo = new Medium {
                    Id = 1, 
                    MediaType = ItemType.Music,
                    Name = "CD"
                }, 
            };

            MediaItem book = new MediaItem {
                Id = 2,
                Name = "Classic Fairy Tales",
                MediaType = ItemType.Book,
                MediumInfo = new Medium {
                    Id = 2, 
                    MediaType = ItemType.Book,
                    Name = "Book"
                }, 
            };

            MediaItem bluRay= new MediaItem {
                Id = 3,
                Name = "The Terminator",
                MediaType = ItemType.Video,
                MediumInfo = new Medium {
                    Id = 2, 
                    MediaType = ItemType.Video,
                    Name = "BluRay"
                }, 
            };

            _items = new List<MediaItem> {
                cd, book, bluRay
            };

            _mediums = new List<string> {
                "All",
                nameof(ItemType.Book),
                nameof(ItemType.Music),
                nameof(ItemType.Video)
            };

            _allItems = new List<MediaItem> {
                cd, book, bluRay
            };
        }

        private void ItemList_Loaded(object sender, RoutedEventArgs e) {
            var listView = (ListView)sender;
            this.PopulateData();
            listView.ItemsSource = _items;
        }

        private void ItemFilter_Loaded(object sender, RoutedEventArgs e) {
            var filterCombo = (ComboBox)sender;
            this.PopulateData();
            filterCombo.ItemsSource = _mediums;
            filterCombo.SelectedIndex = 0;
            ItemFilter.SelectionChanged += (sender, args) => {
                var updatedItems = (from  item in _allItems
                                    where string.IsNullOrWhiteSpace(ItemFilter.SelectedValue.ToString()) 
                                    || ItemFilter.SelectedValue == "All" || ItemFilter.SelectedValue.ToString() == item.MediaType.ToString()
                                    select item).ToList();
                ItemList.ItemsSource = updatedItems;
            };
        }

        public async void AddButton_Clicked(object sender, RoutedEventArgs e) {
            var dialog = new ContentDialog {
                Title = "Add Items to Collection" ,
                Content = "Adding item to the collection is coming soon.",
                CloseButtonText = "OK",
                XamlRoot = Content.XamlRoot
            };

            await dialog.ShowAsync();
        }
    }
}
