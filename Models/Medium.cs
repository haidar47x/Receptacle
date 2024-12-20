using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.UI.Xaml.Automation.Peers;
using Receptacle.Shared.Enums;

namespace Receptacle.Models {
    public class Medium {
        public int Id { get; set; }
        public string Name { get; set; }
        public ItemType MediaType { get; set; }
    }
}
